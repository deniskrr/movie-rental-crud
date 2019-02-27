package controller;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.MovieRepository;

public class MovieController {
    private MovieRepository repo;

    public MovieController(MovieRepository repo) {
        this.repo = repo;
    }

    public void addMovie(Movie movie) throws ValidatorException {
        repo.save(movie);
    }

    public Iterable<Movie> getMovies() {
        return repo.findAll();
    }
}
