package controller;

import domain.Client;
import domain.Movie;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller class containing the application functionality.
 */
public class RentalController {
    private IRepository<UUID, Client> clientRepository;
    private IRepository<UUID, Movie> movieRepository;

    public RentalController(IRepository<UUID, Movie> movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Adds a movie to the repository.
     *
     * @param movie to be added
     * @throws ValidatorException - if the movie is not valid
     */
    public void addMovie(Movie movie) throws ValidatorException {
        movieRepository.save(movie);
    }


    private void populate() {
        addMovie(new Movie("Denis", 10.0,1998, "Comedy"));
        addMovie(new Movie("Denis 2", 5.0,2001, "Comedy"));
        addMovie(new Movie("Oana", 10.0,2008, "Action"));
        addMovie(new Movie("Idk", 1.0,1998, "Horror"));
        addMovie(new Movie("Idk 2", 5.0,2018, "Thriller"));
    }

    public Set<Movie> getMovies() {
       return  StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    public String findMostPopularGenre() {
        return getMovies().stream()
                .collect(Collectors.groupingBy(Movie::getGenre,Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    public List<Movie> filterMovies(Predicate<Movie> predicate) {
        return getMovies().stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Movie> sortByTitle() {
        return getMovies().stream().sorted(Comparator.comparing(Movie::getTitle)).collect(Collectors.toList());
    }

}
