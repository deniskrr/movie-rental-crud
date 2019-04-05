package service;

import domain.Movie;

import java.util.concurrent.Future;

public interface MovieService {
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1235;

    String ADD_MOVIE = "addMovie";

    Future<String> addMovie(Movie movie);


}
