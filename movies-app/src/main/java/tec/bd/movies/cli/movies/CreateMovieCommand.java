package tec.bd.movies.cli.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import tec.bd.movies.ApplicationContext;
import tec.bd.movies.entities.Category;
import tec.bd.movies.entities.Movie;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;

@Command(name = "movie-new", description = "Create new movie in catalog ")
public class CreateMovieCommand implements Callable<Integer> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CreateMovieCommand.class);

    private static ApplicationContext applicationContext = ApplicationContext.init();

    @Option(names = {"-r" }, description = "Creates new movie with random values", defaultValue = "false")
    private boolean createRandomMovieValuesEnabled = false;

    @Option(names = {"-sp" }, description = "Insert Movie via Stored procedure", defaultValue = "false")
    private boolean createViaStoredProcedure = false;

    @Parameters(paramLabel = "<title>", description = "The movie title")
    private String movieTitle;

    @Parameters(paramLabel = "<release date>", description = "Release date")
    private Date movieReleaseDate;

    @Parameters(paramLabel = "<category id>", description = "The movie category id")
    private int movieCategoryId;


    @Override
    public Integer call() throws Exception {

        var category = new Category();
        var movie = new Movie();

        if(createRandomMovieValuesEnabled) {
            category.setCategoryId(1);
            movie.setTitle(UUID.randomUUID().toString());
            movie.setReleaseDate(new Date());
            movie.setCategory(category);
        } else {
            category.setCategoryId(movieCategoryId);
            movie.setTitle(movieTitle);
            movie.setReleaseDate(movieReleaseDate);
            movie.setCategory(category);
        }
        try {
            applicationContext.featureFlags.setCreateMovieViaStoredProcedureEnabled(createViaStoredProcedure);
            var newMovie = applicationContext.movieService.newMovie(movie);

            System.out.println("Movie Id: " + newMovie.getMovieId());
            System.out.println("Title: " + newMovie.getTitle());
            System.out.println("Release Date: " + newMovie.getReleaseDateOnly());
            System.out.println("Category: " + newMovie.getCategory().getCategoryName());
            return 0;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return 1;
        }
    }
}
