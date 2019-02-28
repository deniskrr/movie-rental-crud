package controller;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.util.UUID;

public class MovieController {
    private IRepository<UUID, Movie> repo;

    public MovieController(IRepository<UUID, Movie> repo) {
        this.repo = repo;
    }

    public void addMovie(Movie movie) throws ValidatorException {
        repo.save(movie);
    }

    public Iterable<Movie> getMovies() {
        return repo.findAll();
    }
}
