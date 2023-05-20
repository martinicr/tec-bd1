package tec.bd.movies;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import tec.bd.movies.repository.CategoryRepository;
import tec.bd.movies.repository.CategoryRepositoryImpl;
import tec.bd.movies.repository.MovieRepository;
import tec.bd.movies.repository.MovieRepositoryImpl;
import tec.bd.movies.services.FeatureFlags;
import tec.bd.movies.services.MovieService;
import tec.bd.movies.services.MovieServiceImpl;

public class ApplicationContext {

    public FeatureFlags featureFlags;

    public CategoryRepository categoryRepository;
    public MovieRepository movieRepository;
    public MovieService movieService;

    private ApplicationContext() {

    }

    public static ApplicationContext init() {
        ApplicationContext appContext = new ApplicationContext();

        var hikariConfig = initHikariDataSource();

        appContext.featureFlags = new FeatureFlags();
        appContext.categoryRepository = initCategoryRepository(hikariConfig);
        appContext.movieRepository = initMovieRepository(hikariConfig);
        appContext.movieService = initMovieService(appContext.movieRepository,
                appContext.categoryRepository,
                appContext.featureFlags);

        return appContext;
    }

    private static HikariDataSource initHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        var jdbcUrl = System.getenv("MOVIES_DB_JDBC");
        var username = System.getenv("MOVIES_DB_USERNAME");
        var password = System.getenv("MOVIES_DB_PASSWORD");
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("connectionTestQuery", "SELECT 1");
        hikariConfig.addDataSourceProperty("maximumPoolSize", "4");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(hikariConfig);
    }

    private static CategoryRepository initCategoryRepository(HikariDataSource hikariDataSource) {
        return new CategoryRepositoryImpl(hikariDataSource);
    }
    private static MovieRepository initMovieRepository(HikariDataSource hikariDataSource) {
        return new MovieRepositoryImpl(hikariDataSource);
    }

    private static MovieService initMovieService(MovieRepository movieRepository,
                                                 CategoryRepository categoryRepository,
                                                 FeatureFlags featureFlags) {
        return new MovieServiceImpl(movieRepository, categoryRepository, featureFlags);
    }

}
