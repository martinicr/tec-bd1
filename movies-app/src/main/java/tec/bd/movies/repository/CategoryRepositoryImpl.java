package tec.bd.movies.repository;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.movies.entities.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static tec.bd.movies.repository.Queries.CATEGORY_FIND_BY_ID_QUERY;
import static tec.bd.movies.repository.Queries.MOVIES_FIND_BY_ID_QUERY;

public class CategoryRepositoryImpl extends BaseRepository<Category, Integer> implements CategoryRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

    public CategoryRepositoryImpl(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Optional<Category> findById(Integer entityId) {
        try {
            PreparedStatement stmt = this.connect().prepareStatement(CATEGORY_FIND_BY_ID_QUERY);
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
    public Category save(Category entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    protected Category toEntity(ResultSet resultSet) throws SQLException {
        return new Category(
                resultSet.getInt("id"),
                resultSet.getString("category_name")
        );
    }
}
