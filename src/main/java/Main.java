import controller.MovieController;
import domain.Movie;
import domain.Validator.MovieValidator;
import domain.Validator.Validator;
import repo.MovieFileRepository;
import ui.Console;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> validator = new MovieValidator();
        MovieFileRepository repo = new MovieFileRepository(validator,"movies.txt");
        MovieController ctrl = new MovieController(repo);
        Console console = new Console(ctrl);

        console.run();

    }
}
