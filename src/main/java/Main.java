import controller.ClientController;
import controller.MovieController;
import controller.RentalController;
import domain.Client;
import domain.Movie;
import domain.Rental;
import domain.Validator.ClientValidator;
import domain.Validator.MovieValidator;
import domain.Validator.RentalValidator;
import domain.Validator.Validator;
import repo.IRepository;
import repo.InMemoryRepository;
import repo.db.ClientDatabaseRepository;
import repo.db.MovieDatabaseRepository;
import repo.db.RentalDatabaseRepository;
import repo.file.ClientFileRepository;
import repo.file.MovieFileRepository;
import repo.file.RentalFileRepository;
import repo.xml.ClientXmlRepository;
import repo.xml.MovieXmlRepository;
import repo.xml.RentalXmlRepository;
import ui.Console;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rental> rentalValidator = new RentalValidator();

        IRepository<UUID, Movie> movieRepo = new MovieXmlRepository(movieValidator, "data/movies.xml");
        IRepository<UUID, Client> clientRepo = new ClientXmlRepository(clientValidator, "data/clients.xml");
        IRepository<UUID, Rental> rentalRepo = new RentalXmlRepository(rentalValidator, "data/rentals.xml");

//
//        IRepository<UUID, Movie> movieRepo = new MovieFileRepository(movieValidator, "data/movies.txt");
//        IRepository<UUID, Client> clientRepo = new ClientFileRepository(clientValidator, "data/clients.txt");
//        IRepository<UUID, Rental> rentalRepo = new RentalFileRepository(rentalValidator, "data/rentals.txt");
//
//        IRepository<UUID, Movie> movieRepo = new MovieDatabaseRepository(movieValidator);
//        IRepository<UUID, Client> clientRepo = new ClientDatabaseRepository(clientValidator);
//        IRepository<UUID, Rental> rentalRepo = new RentalDatabaseRepository(rentalValidator);
        MovieController movieController = new MovieController(movieRepo);
        ClientController clientController = new ClientController(clientRepo);
        RentalController rentalController = new RentalController(rentalRepo);
        Console console = new Console(movieController, clientController, rentalController);

        console.run();


    }
}
