package tec.bd;

import com.zaxxer.hikari.*;
import tec.bd.catalog.entity.Category;
import tec.bd.catalog.entity.Videogame;
import tec.bd.catalog.repository.VideogameRepository;
import tec.bd.catalog.repository.VideogameRepositoryImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        // 1. Configurar HikariCP

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/videogame_catalog");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("rootroot");
        hikariConfig.addDataSourceProperty("connectionTestQuery", "SELECT 1");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "4");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        // 2. Configurar VideogameRepository

        VideogameRepository videogameRepository = new VideogameRepositoryImpl(dataSource);

        // 3. Ejecutar FindAll()

        List<Videogame> videogames = videogameRepository.findAll();
        for(Videogame v : videogames) {
            System.out.println(v.getId() + "\t" + v.getTitle() + "\t" + v.getCategory().getName());
        }

        // 4. Insertar un registro

        Category category = new Category();
        category.setId(1);
        Videogame videogame = new Videogame();
        videogame.setCategory(category);
        videogame.setTitle("Super Mario Bros");

        Videogame newVideoGame = videogameRepository.save(videogame);

        System.out.println("NEW: " + newVideoGame.getId() + "\t" + newVideoGame.getTitle() + "\t" + newVideoGame.getCategory().getId());

        List<Videogame> videogames2 = videogameRepository.findAll();
        for(Videogame v : videogames2) {
            System.out.println(v.getId() + "\t" + v.getTitle() + "\t" + v.getCategory().getName());
        }

    }
}
