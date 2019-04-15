package service;

import domain.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService extends Service {

    Optional<Movie> addMovie(String movieParams);

    Optional<Boolean> deleteMovie(UUID uid);

    List<Movie> getMovies();

}
