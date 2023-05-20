package tec.bd.movies.repository;

import com.zaxxer.hikari.HikariDataSource;
import tec.bd.movies.entities.Category;
import tec.bd.movies.entities.Movie;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static tec.bd.movies.repository.Queries.*;

public class MovieRepositoryImpl extends BaseRepository<Movie, Integer> implements MovieRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieRepositoryImpl.class);
    public MovieRepositoryImpl(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    public List<Movie> findAll() {
        try {
            return this.query(MOVIES_FIND_ALL_QUERY);
        } catch (SQLException e) {
            LOGGER.error("Error when finding movies in the DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findById(Integer entityId) {
        try {
            PreparedStatement stmt = this.connect().prepareStatement(MOVIES_FIND_BY_ID_QUERY);
            stmt.setInt(1, entityId);
            return this.query(stmt)
                    .stream()
                    .findFirst();
        } catch (SQLException e) {
            LOGGER.error("Error when finding movie by Id in the DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie save(Movie entity) {
        try(var conn = this.connect()) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); // Creo que mas util en Updates
            Savepoint savepointOne = conn.setSavepoint("savepoint_one");
            try (PreparedStatement stmt = conn.prepareStatement(MOVIES_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, entity.getTitle());
                stmt.setDate(2, new java.sql.Date(entity.getReleaseDate().getTime()));
                stmt.setInt(3, entity.getCategory().getCategoryId());
                var rowsAffected = stmt.executeUpdate(); // se realiza la operacion de escritura
                conn.commit();

                LOGGER.debug("Rows Affected {}", rowsAffected);

                var resultSet = stmt.getGeneratedKeys();

                if (resultSet.next()) {
                    var lastInsertedId = resultSet.getInt(1);
                    LOGGER.debug("Last insert id {}", lastInsertedId);
                    return this.findById(lastInsertedId).get();
                }
            } catch (SQLException e) {
                try {
                    LOGGER.debug("Transaction is being rolled out");
                    conn.rollback(savepointOne);
                } catch (Exception rex) {
                    LOGGER.error("Cant rollback savepointOne operation", rex);
                    throw new RuntimeException(rex);
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error("Error when saving movie in the DB", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int callCreateMovieProcedure(Movie movie) {
        try {
            var cstmt = this.connect().prepareCall(MOVIES_CREATE_MOVIE_PROC_CALL);
            cstmt.setString("p_title", movie.getTitle());
            cstmt.setDate("p_release_date", new java.sql.Date(movie.getReleaseDate().getTime()));
            cstmt.setInt("p_category_id", movie.getCategory().getCategoryId());
            cstmt.registerOutParameter("p_new_movie_id", Types.INTEGER);
            cstmt.executeUpdate();
            var newMovieId = cstmt.getInt("p_new_movie_id");
            cstmt.close();
            return newMovieId;
        } catch (SQLException e) {
            LOGGER.error("Error when invoking stored procedure", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer entityId) {
        try {
            PreparedStatement stmt = this.connect().prepareStatement(MOVIES_DELETE_MOVIE_ID);
            stmt.setInt(1, entityId);
            int affectedRows = stmt.executeUpdate();
            LOGGER.debug("Delete Movie, affected rows {}", affectedRows);
        } catch (SQLException e) {
            LOGGER.error("Error when deleting movie by Id in the DB", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie update(Movie entity) {
//        conn.setAutoCommit(false);
//        conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED); // Creo que mas util en Updates
//        conn.setSavepoint("savepoint_one");
        return null;
    }

    @Override
    protected Movie toEntity(ResultSet resultSet) throws SQLException {
        var category = new Category(
                resultSet.getInt("category_id"),
                resultSet.getString("category_name")
        );
        return new Movie(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDate("release_date"),
                category
        );
    }

}
