package ui;

import controller.ClientController;
import controller.MovieController;
import controller.RentalController;
import domain.Client;
import domain.Movie;
import domain.Rental;
import domain.Validator.InvalidCommandException;
import domain.Validator.ValidatorException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Class responsible for I/O operations
 */
public class Console {

    private Scanner scanner = new Scanner(System.in);
    private MovieController movieController;
    private ClientController clientController;
    private RentalController rentalController;

    public Console(MovieController movieController, ClientController clientController, RentalController rentalController) {
        this.movieController = movieController;
        this.clientController = clientController;
        this.rentalController = rentalController;
    }

    private Client readClient() {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name ");
        String lastName = scanner.nextLine();
        System.out.print("Year:");
        int year = readInt();

        return new Client(firstName, lastName, year);
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
        System.out.print("Year: ");
        int year = readInt();
        System.out.print("Genre:");
        String genre = scanner.nextLine();

        return new Movie(title, rating, year, genre);
    }

    private Rental readRental() {
        System.out.println("Choose the client:");
        int index = 1;
        for (Client client : clientController.getClients()) {
            System.out.println("\t" + index++ + ". " + client);
        }
        int clientIndex = readInt();
        index = 1;
        for (Movie movie : movieController.getMovies()) {
            System.out.println("\t" + index++ + ". " + movie);
        }
        int movieIndex = readInt();
        UUID clientID = clientController.getClients().get(clientIndex-1).getId();
        UUID movieID = movieController.getMovies().get(movieIndex-1).getId();

        return new Rental(clientID, movieID);
    }

    private UUID readID() {
        return UUID.fromString(scanner.nextLine());
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
        movieController.getMovies().forEach(System.out::println);
    }

    private void printClients() {
        clientController.getClients().forEach(System.out::println);
    }

    private void printRentals() {
        rentalController.getRentals().forEach((rental -> {
            Client client = clientController.getClient(rental.getClientID());
            Movie movie = movieController.getMovie(rental.getMovieID());
            if (client != null && movie != null) {
                System.out.println("Client:" + client + " --- Movie:" +movie);
            } else {
                rentalController.deleteRental(rental.getId());
            }
        }));
    }

    private void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Movie menu");
        System.out.println("2. Client menu");
        System.out.println("3. Rental menu");
    }

    /**
     * Prints the movie menu.
     */
    private void printMovieMenu() {
        System.out.println("1. Add movie");
        System.out.println("2. Print all movies");
        System.out.println("3. Filter movies");
        System.out.println("4. Find most popular genre");
        System.out.println("5. Sort by title");
        System.out.println("6. Delete movie");
    }

    /**
     * Prints the movie menu.
     */
    private void printClientMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Print all clients");
        System.out.println("3. Delete client");
    }

    private void printRentalMenu() {
        System.out.println("1. Rent a movie");
        System.out.println("2. Print all rentals");
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
        while (true) {
            printMenu();
            int menuChoice = readInt();
            switch (menuChoice) {
                case 0:
                    exit();
                    break;
                case 1:
                    movieMenu();
                    break;
                case 2:
                    clientMenu();
                    break;
                case 3:
                    rentalMenu();
                    break;
                default:
                    throw new InvalidCommandException();
            }
        }
    }

    private void movieMenu() {
        printMovieMenu();
        int choice = readInt();
        switch (choice) {
            case 1:
                Movie movie = readMovie();
                try {
                    movieController.addMovie(movie);
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
                        predicate = Movie.isNiceMovie();
                        break;
                    case 2:
                        predicate = Movie.isSequel();
                        break;
                    case 3:
                        predicate = Movie.isOld();
                        break;
                    default:
                        //TODO Add exception
                        System.out.println("Invalid choice");
                        return;
                }
                List<Movie> movieList = movieController.filterMovies(predicate);
                movieList.forEach(System.out::println);
                break;
            case 4:
                System.out.println(movieController.findMostPopularGenre());
                break;
            case 5:
                movieController.sortByTitle().forEach(System.out::println);
                break;
            case 6:
                UUID id = readID();
                System.out.print("ID: ");
                movieController.deleteMovie(id);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    private void clientMenu() {
        printClientMenu();
        int choice = readInt();
        switch (choice) {
            case 1:
                Client client = readClient();
                try {
                    clientController.addClient(client);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                printClients();
                break;
            case 3:
                UUID id = readID();
                System.out.print("ID: ");
                clientController.deleteClient(id);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    private void rentalMenu() {
        printRentalMenu();
        int choice = readInt();
        switch (choice) {
            case 1:
                Rental rental = readRental();
                try {
                    rentalController.addRental(rental);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                printRentals();
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    private void exit() {
        System.out.println("Exit application.");
        System.exit(0);
    }
}
