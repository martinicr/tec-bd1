package tec.bd.movies.cli.movies;

import picocli.CommandLine;
import tec.bd.movies.ApplicationContext;
import tec.bd.movies.entities.Movie;

@CommandLine.Command(name = "movies-all", description = "Get all movies in catalog")
public class GetAllMoviesCommand implements Runnable {

    private static ApplicationContext applicationContext = ApplicationContext.init();

    @Override
    public void run() {
        var movies = applicationContext.movieService.getMovies();

        System.out.println("Movie Catalog");
        System.out.println("Id\t Title\t Release Date\t Category");
        for(Movie m : movies) {
            System.out.println(m.getMovieId() +"\t "+ m.getTitle() +"\t " + m.getReleaseDateOnly() + "\t " + m.getCategory().getCategoryName());
        }
    }
}
