package tec.bd.catalog.repository;

import tec.bd.catalog.entity.Category;
import tec.bd.catalog.entity.Videogame;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideogameRepositoryImpl implements VideogameRepository {

    private DataSource dataSource;

    public VideogameRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Videogame> findAll() {
        try {
            var connection = this.dataSource.getConnection();
            Statement statement = connection.createStatement();
            String query = "select v.id, v.title, v.category_id, c.name " +
                    "from videogame v inner join category c on v.category_id = c.id order by v.id desc";
            ResultSet resultSet = statement.executeQuery(query);
            List<Videogame> entities = new ArrayList<>();
            while(resultSet.next()) {
                Category category = new Category();
                Videogame videogame = new Videogame();

                /*
                id |  title | category_id | name |
                1  | Zelda  | 1           | RPG  |
                2  | Mario  | 2           | Adv  |
                3  | GTA    | 1           | RPG  |
                 */
                category.setId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                videogame.setId(resultSet.getInt("id"));
                videogame.setTitle(resultSet.getString("title"));
                videogame.setCategory(category);
                entities.add(videogame);
            }
            return entities;
        } catch (Exception e) {
            throw new RuntimeException("Error when finding videogame entities", e);
        }
    }

    @Override
    public Optional<Videogame> findById(int id) {
        // PreparedStatement
        return Optional.empty();
    }

    @Override
    public Videogame save(Videogame videogame) {
        try {
            String query = "insert into videogame(title, category_id) values (?, ?)";
            var connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, videogame.getTitle());
            preparedStatement.setInt(2, videogame.getCategory().getId());
            var rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows Affected " + rowsAffected);
            var resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                var lastInsertedId = resultSet.getInt(1);
                Videogame newVideogame = new Videogame();
                newVideogame.setId(lastInsertedId);
                newVideogame.setTitle(videogame.getTitle());
                newVideogame.setCategory(videogame.getCategory());
                return newVideogame;
            }
            throw new RuntimeException("Cant insert videogame");
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception while inserting videogame", e);
        }
    }
}
