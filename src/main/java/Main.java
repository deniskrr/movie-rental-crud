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
import repo.file.ClientFileRepository;
import repo.file.MovieFileRepository;
import repo.file.RentalFileRepository;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rental> rentalValidator = new RentalValidator();
        MovieFileRepository movieRepo = new MovieFileRepository(movieValidator,"data/movies.txt");
        ClientFileRepository clientRepo = new ClientFileRepository(clientValidator,"data/clients.txt");
        RentalFileRepository rentalRepo = new RentalFileRepository(rentalValidator,"data/rentals.txt");
        MovieController movieController = new MovieController(movieRepo);
        ClientController clientController = new ClientController(clientRepo);
        RentalController rentalController = new RentalController(rentalRepo);
        Console console = new Console(movieController, clientController, rentalController);

        console.run();

    }
}
