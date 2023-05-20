package tec.bd.movies.cli.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import tec.bd.movies.ApplicationContext;
import tec.bd.movies.entities.Category;
import tec.bd.movies.entities.Movie;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;

@Command(name = "movie-update", description = "Update movie data in catalog ")
public class UpdateMovieCommand implements Callable<Integer> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UpdateMovieCommand.class);

    private static ApplicationContext applicationContext = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<movie id>", description = "The movie id")
    private int movieId;

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
