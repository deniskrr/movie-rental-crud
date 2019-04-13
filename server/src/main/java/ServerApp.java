import domain.Client;
import domain.Movie;
import domain.Rental;
import domain.Validator.ClientValidator;
import domain.Validator.MovieValidator;
import domain.Validator.RentalValidator;
import domain.Validator.Validator;
import repo.Repository;
import repo.db.ClientDatabaseRepository;
import repo.db.MovieDatabaseRepository;
import service.ClientServiceServerImplementation;
import service.MovieServiceServerImplementation;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rental> rentalValidator = new RentalValidator();
        System.setProperty("username", "postgres");
        System.setProperty("password", "2233");
        Repository<UUID, Movie> movieRepo = new MovieDatabaseRepository(movieValidator);
        Repository<UUID, Client> clientRepository = new ClientDatabaseRepository(clientValidator);
        MovieServiceServerImplementation movieService = new MovieServiceServerImplementation(executorService, movieRepo);
        ClientServiceServerImplementation clientService = new ClientServiceServerImplementation(executorService, clientRepository);

    }
}
