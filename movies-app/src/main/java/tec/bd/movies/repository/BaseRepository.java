package tec.bd.movies.repository;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T, ID extends Serializable> implements CRUDRepository<T, ID> {

    protected DataSource dataSource;

    protected BaseRepository(HikariDataSource hikariDataSource)
    {
        this.dataSource = hikariDataSource;
    }

    protected Connection connect() throws SQLException {
        return this.dataSource.getConnection();
    }

    protected ResultSet rawQuery(String sqlQuery) throws SQLException {
        var connection = this.connect();
        Statement stmt = connection.createStatement();
        //execute query -- consultas de seleccion
        return stmt.executeQuery(sqlQuery);
    }

    protected List<T> query(String sqlQuery) throws SQLException {
        ResultSet resultSet = this.rawQuery(sqlQuery);
        return this.resultSetToEntityList(resultSet);
    }

    protected List<T> query(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return this.resultSetToEntityList(resultSet);
    }

    protected List<T> resultSetToEntityList(ResultSet resultSet) throws SQLException {
        var entities = new ArrayList<T>();
        while(resultSet.next()) {
            var entity = toEntity(resultSet);
            if(null != entity) {
                entities.add(entity);
            }
        }
        return entities;
    }

    // TODO: Crear metodo execute(String sqlQuery)

    // TODO: Crear metodo execute(PreparedStatement stmt)
//    protected boolean execute(PreparedStatement stmt) throws SQLException {
//        return stmt.execute();
//    }

    protected abstract T toEntity(ResultSet resultSet) throws SQLException;

}
