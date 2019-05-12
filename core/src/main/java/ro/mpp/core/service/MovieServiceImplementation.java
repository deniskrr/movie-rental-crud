package ro.mpp.core.service;


import ro.mpp.core.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ro.mpp.core.repo.db.MovieDatabaseRepository;

import java.util.List;

public class MovieServiceImplementation implements MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImplementation.class);

    @Autowired
    private MovieDatabaseRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllStudents - called");

        List<Movie> result = movieRepository.findAll();

        log.trace("getAllStudents - result={}", result);

        return result;

    }

    @Override
    public Movie saveMovie(Movie movie) {
        log.trace("saveMovie - called");

        Movie savedMovie = movieRepository.save(movie);

        log.trace("saveMovie - result={}", savedMovie);

        return savedMovie;

    }

    @Override
    public Movie updateMovie(Long id, Movie convertDtoToModel) {
        return null;
    }

    @Override
    public void deleteMovie(Long id) {
        log.trace("deleteMovie - id={}", id);

        movieRepository.deleteById(id);

        log.trace("deleteMovie - method finished");
    }
}
