package service;

import domain.Movie;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Controller class containing the application functionality.
 */
public class MovieServiceServerImplementation implements MovieService {
    private IRepository<UUID, Movie> movieRepository;
    private ExecutorService executorService;

    public MovieServiceServerImplementation(ExecutorService executorService, IRepository<UUID, Movie> movieRepository) {
        this.movieRepository = movieRepository;
        this.executorService = executorService;
    }

    /**
     * Adds a movie to the repository.
     *
     * @param movie to be added
     * @throws ValidatorException - if the movie is not valid
     */
    public Future<String> addMovie(Movie movie) throws ValidatorException {
        movieRepository.save(movie);
        return null;
    }

    public void deleteMovie(UUID id) {
        movieRepository.delete(id);
    }

    public Movie getMovie(UUID id) {
        if (movieRepository.findOne(id).isPresent()) {
            return movieRepository.findOne(id).get();
        }
        return null;
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
