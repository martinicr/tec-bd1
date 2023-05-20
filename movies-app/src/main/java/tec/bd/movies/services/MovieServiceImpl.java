package tec.bd.movies.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tec.bd.movies.entities.Movie;
import tec.bd.movies.repository.CategoryRepository;
import tec.bd.movies.repository.MovieRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class MovieServiceImpl implements MovieService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;

    private final CategoryRepository categoryRepository;

    private final FeatureFlags featureFlags;

    public MovieServiceImpl(MovieRepository movieRepository, CategoryRepository categoryRepository, FeatureFlags featureFlags) {
        requireNonNull(movieRepository);
        requireNonNull(categoryRepository);
        requireNonNull(featureFlags);

        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.featureFlags = featureFlags;
    }

    @Override
    public List<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(int movieId) {
        // validacion
        if (movieId > 0 ) {
            return this.movieRepository.findById(movieId);
        }
        return Optional.empty();
    }

    @Override
    public Movie newMovie(Movie movie) {
        requireNonNull(movie);
        requireNonNull(movie.getCategory());

        if(movie.getTitle() == null || movie.getTitle().isEmpty() || movie.getTitle().isBlank()) {
            throw new RuntimeException("Movie title is null or empty");
        }
        if(movie.getReleaseDate() == null ) {
            throw new RuntimeException("Release date is not valid");
        }
        if(movie.getCategory().getCategoryId() <= 0) {
            throw new RuntimeException("Category Id " + movie.getTitle() + " is not valid");
        }

        if(this.featureFlags.isCreateMovieViaStoredProcedureEnabled()) {
            LOGGER.debug("Creating new movie via stored procedure");
            var newMovieId = this.movieRepository.callCreateMovieProcedure(movie);
            LOGGER.debug("New MovieId from stored procedure {}", newMovieId);
            return this.getMovieById(newMovieId).get();
        }

        LOGGER.debug("Creating new movie via adhoc SQL commands");
        this.categoryRepository.findById(movie.getCategory().getCategoryId()).orElseThrow();
        return this.movieRepository.save(movie);
    }

    @Override
    public void removeMovie(int movieId) {
        if (movieId < 1) {
            LOGGER.error("Movie id {} is invalid", movieId);
            throw new RuntimeException("Movie id is invalid");
        }

        var movieInCatalog = this.movieRepository.findById(movieId);

        movieInCatalog.ifPresentOrElse((m) -> {
            this.movieRepository.delete(movieId);
        }, () -> {
            LOGGER.debug("Movie id {} doesnt exist in catalog", movieId);
            new RuntimeException("Movie doesnt exists in catalog");
        });
    }

    @Override
    public Movie updateMovie(Movie movie) {
        // Validaciones
        return this.movieRepository.update(movie);
    }
}
