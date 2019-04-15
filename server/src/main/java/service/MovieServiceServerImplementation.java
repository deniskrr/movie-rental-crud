package service;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Controller class responsible for CRUD operations on movie model
 */
public class MovieServiceServerImplementation implements MovieService {
    private Repository<UUID, Movie> movieRepository;

    public MovieServiceServerImplementation(Repository<UUID, Movie> movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Adds a movie to the repository.
     *
     * @param movieParams string containing the movie attributes
     * @throws ValidatorException - if the movie is not valid
     */
    public Optional<Movie> addMovie(String movieParams) throws ValidatorException {
        String[] movieParamsArray = movieParams.split(",");
        Movie movie = new Movie(movieParamsArray[0],
                Double.valueOf(movieParamsArray[1]),
                Integer.valueOf(movieParamsArray[2]),
                movieParamsArray[3]);
        return movieRepository.save(movie);
    }

    public Optional<Boolean> deleteMovie(UUID id) {
        return movieRepository.delete(id);
    }


    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
