package controller;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.util.UUID;

/**
 * Controller class containing the application functionality.
 */
public class MovieController {
    private IRepository<UUID, Movie> repo;

    public MovieController(IRepository<UUID, Movie> repo) {
        this.repo = repo;
    }

    /**
     * Adds a movie to the repository.
     *
     * @param movie to be added
     * @throws ValidatorException - if the movie is not valid
     */
    public void addMovie(Movie movie) throws ValidatorException {
        repo.save(movie);
    }

    /**
     * Gets the list of movies.
     *
     * @return an {@code Iterable} containing the movies
     */
    public Iterable<Movie> getMovies() {
        return repo.findAll();
    }
}
