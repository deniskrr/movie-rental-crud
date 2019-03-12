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

    public RentalController(IRepository<UUID, Movie> movieRepository, IRepository<UUID, Client> clientRepository) {
        this.movieRepository = movieRepository;
        this.clientRepository = clientRepository;
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

    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    public Set<Movie> getMovies() {
       return  StreamSupport.stream(movieRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    public Set<Client> getClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toSet());
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
