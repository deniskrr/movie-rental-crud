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
    private ExecutorService executorService;

    public MovieServiceServerImplementation(ExecutorService executorService, Repository<UUID, Movie> movieRepository) {
        this.movieRepository = movieRepository;
        this.executorService = executorService;
    }

    /**
     * Adds a movie to the repository.
     *
     * @param movieParams string containing the movie attributes
     * @throws ValidatorException - if the movie is not valid
     */
    public CompletableFuture<Optional<Movie>> addMovie(String movieParams) throws ValidatorException {
        String[] movieParamsArray = movieParams.split(",");
        Movie movie = new Movie(movieParamsArray[0],
                Double.valueOf(movieParamsArray[1]),
                Integer.valueOf(movieParamsArray[2]),
                movieParamsArray[3]);
        return CompletableFuture.supplyAsync(() -> movieRepository.save(movie), executorService);
    }

    public CompletableFuture<Optional<Boolean>> deleteMovie(UUID id) {
        return CompletableFuture.supplyAsync(() -> movieRepository.delete(id), executorService);
    }


    public CompletableFuture<List<Movie>> getMovies() {
        return CompletableFuture.supplyAsync(() -> movieRepository.findAll(), executorService);
    }
}
