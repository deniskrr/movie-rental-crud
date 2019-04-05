package service;

import domain.Movie;

import java.util.concurrent.Future;

public interface MovieService extends Service {
    String ADD_MOVIE = "addMovie";

    Future<String> addMovie(Movie movie);


}
