import controller.MovieController;
import domain.Movie;
import domain.Validator.MovieValidator;
import domain.Validator.Validator;
import repo.IRepository;
import repo.MovieRepository;
import ui.Console;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> validator = new MovieValidator();
        IRepository<UUID, Movie>  repo = new MovieRepository(validator);
        MovieController ctrl = new MovieController(repo);
        Console console = new Console(ctrl);

        console.run();

    }
}
