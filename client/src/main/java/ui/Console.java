package ui;

import service.ClientService;
import service.MovieService;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Class responsible for I/O operations
 */
public class Console {

    private Scanner scanner = new Scanner(System.in);
    private MovieService movieService;
    private ClientService clientService;


    public Console(MovieService movieService, ClientService clientService) {
        this.movieService = movieService;
        this.clientService = clientService;
    }

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
            }
        }
    }

    private void movieMenu() {
        printMovieMenu();
        int choice = readInt();

        switch (choice) {

            case 1:
                String movie = readMovie();
                movieService.addMovie(movie);
                break;
            case 2:
                String id = scanner.nextLine();
                movieService.deleteMovie(UUID.fromString(id));
                break;
            case 3:
                movieService.getMovies();

        }
    }

    private void clientMenu() {
        printClientMenu();
        int choice = readInt();
        switch (choice) {
            case 1:
                String client = readClient();
                try {
                    clientService.addClient(client).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                String id = scanner.nextLine();
                try {
                    clientService.deleteClient(UUID.fromString(id)).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
        }
    }

    private String readClient() {
        String client = "";
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        client += firstName + ",";
        System.out.print("Last name ");
        String lastName = scanner.nextLine();
        client += lastName + ",";
        System.out.print("Year:");
        int year = readInt();
        client += year;

        return client;
    }


    /**
     * Reads a movie from the user.
     *
     * @return a movie parameters string
     */
    private String readMovie() {
        String movie = "";
        System.out.print("Title: ");
        String title = scanner.nextLine();
        movie += title + ",";
        System.out.print("Rating: ");
        double rating = readDouble();
        movie += rating + ",";
        System.out.print("Year: ");
        int year = readInt();
        movie += year + ",";
        System.out.print("Genre:");
        String genre = scanner.nextLine();
        movie += "genre";

        return movie;
    }
//
//    private Rental readRental() {
//        System.out.println("Choose the client:");
//        int index = 1;
//        for (Client client : clientController.getClients()) {
//            System.out.println("\t" + index++ + ". " + client);
//        }
//        int clientIndex = readInt();
//        index = 1;
//        for (Movie movie : movieController.getMovies()) {
//            System.out.println("\t" + index++ + ". " + movie);
//        }
//        int movieIndex = readInt();
//        UUID clientID = clientController.getClients().get(clientIndex-1).getId();
//        UUID movieID = movieController.getMovies().get(movieIndex-1).getId();
//
//        return new Rental(clientID, movieID);
//    }
//
//    private UUID readID() {
//        return UUID.fromString(scanner.nextLine());
//    }
//

    /**
     * Reads an int.
     *
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
//

    /**
     * Reads a double.
     *
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

    //    /**
//     * Prints the movies.
//     */
//    private void printMovies() {
//        movieController.getMovies().forEach(System.out::println);
//    }
//
//    private void printClients() {
//        clientController.getClients().forEach(System.out::println);
//    }
//
//    private void printRentals() {
//        rentalController.getRentals().forEach((rental -> {
//            Client client = clientController.getClient(rental.getClientID());
//            Movie movie = movieController.getMovie(rental.getMovieID());
//            if (client != null && movie != null) {
//                System.out.println("Client:" + client + " --- Movie:" +movie);
//            } else {
//                rentalController.deleteRental(rental.getId());
//            }
//        }));
//    }
//
//    private void printRentalCount(){
//        rentalController.getRentals().stream().collect(Collectors.groupingBy(Rental::getMovieID, Collectors.counting())).entrySet().stream().forEach(entry ->
//                System.out.println(movieController.getMovie(entry.getKey()).getTitle() + " - " + entry.getValue()));
//    }
//
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
        System.out.println("2. Delete movie");
        System.out.println("3. Get movie");
    }

//    /**
//     * Prints the movie menu.
//     */

    private void printClientMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Delete client");
        System.out.println("3. Get client");
    }

//    private void printRentalMenu() {
//        System.out.println("1. Rent a movie");
//        System.out.println("2. Print all rentals");
//        System.out.println("3. Print the most rented movie");
//        System.out.println("4. Print how many times each movie was rented");
//    }
//
//    private void printFilterMenu() {
//        System.out.println("1. Is nice movie");
//        System.out.println("2. Is sequel");
//        System.out.println("3. Is old");
//    }
//
//
//
//    /**
//     * Application loop.
//     */
//    public void run() {
//        while (true) {
//            printMenu();
//            int menuChoice = readInt();
//            switch (menuChoice) {
//                case 0:
//                    exit();
//                    break;
//                case 1:
//                    movieMenu();
//                    break;
//                case 2:
//                    clientMenu();
//                    break;
//                case 3:
//                    rentalMenu();
//                    break;
//                default:
//                    throw new InvalidCommandException();
//            }
//        }
//    }

    //    private void clientMenu() {
//        printClientMenu();
//        int choice = readInt();
//        switch (choice) {
//            case 1:
//                Client client = readClient();
//                try {
//                    clientController.addClient(client);
//                } catch (ValidatorException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 2:
//                printClients();
//                break;
//            case 3:
//                UUID id = readID();
//                System.out.print("ID: ");
//                clientController.deleteClient(id);
//                break;
//            default:
//                throw new InvalidCommandException();
//        }
//    }
//
//    private void rentalMenu() {
//        printRentalMenu();
//        int choice = readInt();
//        switch (choice) {
//            case 1:
//                Rental rental = readRental();
//                try {
//                    rentalController.addRental(rental);
//                } catch (ValidatorException e) {
//                    e.printStackTrace();
//                }
//                break;
//            case 2:
//                printRentals();
//                break;
//            case 3:
//                UUID id = rentalController.getMostRentedMovie();
//                System.out.println(movieController.getMovie(id));
//                break;
//            case 4:
//                printRentalCount();
//                break;
//            default:
//                throw new InvalidCommandException();
//        }
//    }
//
    private void exit() {
        System.out.println("Exit application.");
        System.exit(0);
    }
}
