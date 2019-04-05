package controller;

import domain.Movie;
import domain.Validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller class containing the application functionality.
 */

@Service
public class MovieController {

    private final Repository<UUID, Movie> movieRepository;

    @Autowired
    public MovieController(Repository<UUID, Movie> movieRepository) {
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

    public void deleteMovie(UUID id) {
//        movieRepository.delete(id);
    }


    public List<Movie> getSortedMoviesYear(int year) {
        List<Movie> initial = getMovies().stream().filter(movie -> movie.getYear() > year).sorted(Comparator.comparing(Movie::getTitle)).collect(Collectors.toList());
        getMovies().stream().filter(movie -> movie.getYear() < year).sorted(Comparator.comparing(Movie::getTitle).reversed()).forEach(initial::add);
        return initial;
    }

    public List<Movie> getMovies() {
       return  StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toList());
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
