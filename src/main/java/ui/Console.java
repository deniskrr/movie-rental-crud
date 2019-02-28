package ui;

import controller.MovieController;
import domain.Movie;
import domain.Validator.ValidatorException;

import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);
    private MovieController ctrl;

    public Console(MovieController ctrl) {
        this.ctrl = ctrl;
    }

    private Movie readMovie() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Rating: ");
        double rating = readDouble();
        System.out.println("Year: ");
        int year = readInt();

        return new Movie(title, rating, year);
    }

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

    private void printMovies() {

        ctrl.getMovies().forEach(System.out::println);
    }

    private void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Add movie");
        System.out.println("2. Print all movies");
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt();
            switch (choice) {
                case 0:
                    running = false;
                    break;
                case 1:
                    Movie movie = readMovie();
                    System.out.println(movie);
                    try {
                        ctrl.addMovie(movie);
                    } catch (ValidatorException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    printMovies();
                    break;
            }
        }
    }
}
