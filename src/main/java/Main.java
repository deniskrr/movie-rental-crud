import controller.RentalController;
import domain.Client;
import domain.Movie;
import domain.Validator.ClientValidator;
import domain.Validator.MovieValidator;
import domain.Validator.Validator;
import repo.ClientFileRepository;
import repo.MovieFileRepository;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        MovieFileRepository movieRepo = new MovieFileRepository(movieValidator,"movies.txt");
        ClientFileRepository clientRepo = new ClientFileRepository(clientValidator,"clients.txt");
        RentalController ctrl = new RentalController(movieRepo, clientRepo);
        Console console = new Console(ctrl);

        console.run();

    }
}
