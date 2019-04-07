package service;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.Repository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Future<String> addMovie(String movieParams) throws ValidatorException {
        String[] movieParamsArray = movieParams.split(",");
        Movie movie = new Movie(movieParamsArray[0],
                Double.valueOf(movieParamsArray[1]),
                Integer.valueOf(movieParamsArray[2]),
                movieParamsArray[3]);
        return CompletableFuture.supplyAsync(() -> movieRepository.save(movie), executorService)
                .thenApply((optional) -> {
                    if (optional.isPresent()) {
                        return "Movie was added to the repository.";
                    } else {
                        return "Movie was NOT added to the repository.";
                    }
                });
    }

    public Future<String> deleteMovie(UUID id) {
        return CompletableFuture.supplyAsync(() -> movieRepository.delete(id), executorService)
                .thenApply((optional) -> {
                    if (optional.isPresent()) {
                        if (optional.get() == true) {
                            return "Movie was deleted from the repository";
                        } else {
                            return "Movie was not found in the repository";
                        }
                    } else {
                        return "Movie was NOT deleted from the repository";
                    }
                });
    }

    public Future<String> getMovie(UUID id) {
        return CompletableFuture.supplyAsync(() -> movieRepository.findOne(id), executorService)
                .thenApply((optional) -> {
                    if (optional.isPresent()) {
                        return optional.get().toString();
                    } else {
                        return "Movie not found";
                    }
                });
    }

    public List<Movie> getMovies() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }



}
