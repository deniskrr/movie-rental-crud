package service;

import java.util.UUID;
import java.util.concurrent.Future;

public interface MovieService extends Service {
    String ADD_MOVIE = "addMovie";
    String DELETE_MOVIE = "deleteMovie";
    String GET_MOVIE = "getMovie";
    String GET_MOVIES = "getMovies";

    Future<String> addMovie(String movieParams);

    Future<String> deleteMovie(UUID uid);

    Future<String> getMovie(UUID uid);

    Future<String> getMovies();

}
