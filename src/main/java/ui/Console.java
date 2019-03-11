package ui;

import controller.MovieController;
import domain.Movie;
import domain.Validator.ValidatorException;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Class responsible for I/O operations
 */
public class Console {

    private Scanner scanner = new Scanner(System.in);
    private MovieController ctrl;

    public Console(MovieController ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Reads a movie from the user.
     * @return a new instance of {@link Movie}
     */
    private Movie readMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Rating: ");
        double rating = readDouble();
        System.out.println("Year: ");
        int year = readInt();

        return new Movie(title, rating, year);
    }

    /**
     * Reads an int.
     * @return the read int
     */
    private int readInt() {
        while (true) {
            int numChoice = 0;
            String choice = scanner.nextLine();
            try {
                numChoice = Integer.parseInt(choice);
                return numChoice;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }

        }
    }

    /**
     * Reads a double.
     * @return the read double
     */
    private double readDouble() {
        while (true) {
            double numChoice = 0;
            String choice = scanner.nextLine();
            try {
                numChoice = Double.parseDouble(choice);
                return numChoice;
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Prints the movies.
     */
    private void printMovies() {
        ctrl.getMovies().forEach(System.out::println);
    }

    /**
     * Prints the menu.
     */
    private void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Add movie");
        System.out.println("2. Print all movies");
        System.out.println("3. Filter movies");
    }

    private void printFilterMenu() {
        System.out.println("1. Is nice movie");
        System.out.println("2. Is sequel");
        System.out.println("3. Is old");
    }

    /**
     * Application loop.
     */
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt();
            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Exit app.. Bye!");
                    break;
                case 1:
                    Movie movie = readMovie();
                    try {
                        ctrl.addMovie(movie);
                    } catch (ValidatorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    printMovies();
                    break;
                case 3:
                    printFilterMenu();
                    int pred = readInt();
                    Predicate<Movie> predicate = null;
                    switch (pred) {
                        case 1:
                            predicate = MovieController.isNiceMovie();
                            break;
                        case 2:
                            predicate = MovieController.isSequel();
                            break;
                        case 3:
                            predicate = MovieController.isOld();
                            break;
                        default:
                            //TODO Add exception
                            System.out.println("Invalid choice");
                            return;
                    }
                    List<Movie> movieList = ctrl.filterMovies(predicate);
                    movieList.forEach(System.out::println);
                    break;
            }
        }
    }
}
