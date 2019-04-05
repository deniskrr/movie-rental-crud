package service;

import java.util.concurrent.Future;

public interface MovieService extends Service {
    String ADD_MOVIE = "addMovie";

    Future<String> addMovie(String movieParams);


}
