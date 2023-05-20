package tec.bd.movies.cli.movies;

import picocli.CommandLine;
import tec.bd.movies.ApplicationContext;

@CommandLine.Command(name = "movie-id", description = "Get movie in catalog by id")
public class GetMovieCommand implements Runnable {

    private static ApplicationContext applicationContext = ApplicationContext.init();
    @CommandLine.Parameters(paramLabel = "<movie id>", description = "The movie id")
    private int movieId;

    @Override
    public void run() {

        applicationContext.movieService.getMovieById(movieId).ifPresentOrElse((movie) -> {
            System.out.println("Movie Id: " + movie.getMovieId());
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Release Date: " + movie.getReleaseDateOnly());
            System.out.println("Category: " + movie.getCategory().getCategoryName());
        }, () -> System.out.println("Movie id " + movieId + " not found"));
    }
}
