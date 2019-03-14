import controller.ClientController;
import controller.MovieController;
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
        MovieController movieController = new MovieController(movieRepo);
        ClientController clientController = new ClientController(clientRepo);
        Console console = new Console(movieController, clientController);

        console.run();

    }
}
