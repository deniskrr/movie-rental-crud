package service;

import domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MovieServiceClientImplementation implements MovieService {
    @Autowired
    private MovieService movieService;

    private ExecutorService executorService;

    public MovieServiceClientImplementation(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Optional<Movie> addMovie(String movieParams){
        return movieService.addMovie(movieParams);
    }

    @Override
    public Optional<Boolean> deleteMovie(UUID uid) {
        return movieService.deleteMovie(uid);
    }

    @Override
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }
}

