package tec.bd.movies.repository;

import tec.bd.movies.entities.Movie;

import java.util.Date;

public interface MovieRepository extends CRUDRepository<Movie, Integer> {

    int callCreateMovieProcedure(Movie movie);
}
