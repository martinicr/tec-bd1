package tec.bd.movies.services;

import tec.bd.movies.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> getMovies();

    Optional<Movie> getMovieById(int movieId);

    Movie newMovie(Movie movie);

    void removeMovie(int movieId);

    Movie updateMovie(Movie movie);

}
