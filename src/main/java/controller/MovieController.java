package controller;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.MovieRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Controller class containing the application functionality.
 */
public class MovieController {
    private MovieRepository repo;

    public static Predicate<Movie> isNiceMovie() {
        return p -> p.getRating() > 8.0;
    }

    public static Predicate<Movie> isSequel() {
        return p -> p.getTitle().endsWith("2");
    }

    public static Predicate<Movie> isOld() {
        return p -> p.getYear() < 2005;
    }

    public MovieController(MovieRepository repo) {
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

    public List<Movie> filterMovies(Predicate<Movie> predicate) {
        return repo.getMovies().stream().filter(predicate).collect(Collectors.toList());
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
