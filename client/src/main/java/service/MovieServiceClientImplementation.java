package service;

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
    public Future<String> addMovie(String movieParams) {
        return null;
    }

    @Override
    public Future<String> deleteMovie(UUID uid) {
        return null;
    }

    @Override
    public Future<String> getMovies() {
        return null;
    }
}

