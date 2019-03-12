import controller.RentalController;
import domain.Movie;
import domain.Validator.MovieValidator;
import domain.Validator.Validator;
import repo.MovieFileRepository;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        Validator<Movie> validator = new MovieValidator();
        MovieFileRepository repo = new MovieFileRepository(validator,"movies.txt");
        RentalController ctrl = new RentalController(repo);
        Console console = new Console(ctrl);

        console.run();

    }
}
