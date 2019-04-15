package service;

import domain.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MovieService extends Service {

    CompletableFuture<Optional<Movie>> addMovie(String movieParams);

    CompletableFuture<Optional<Boolean>> deleteMovie(UUID uid);

    CompletableFuture<List<Movie>> getMovies();

}
