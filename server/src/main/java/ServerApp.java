import domain.Client;
import domain.Message;
import domain.Movie;
import domain.Rental;
import domain.Validator.ClientValidator;
import domain.Validator.MovieValidator;
import domain.Validator.RentalValidator;
import domain.Validator.Validator;
import repo.Repository;
import repo.db.MovieDatabaseRepository;
import service.MovieService;
import service.MovieServiceServerImplementation;
import tcp.TcpServer;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        TcpServer tcpServer = new TcpServer(executorService,
                MovieService.SERVER_PORT);

        Validator<Movie> movieValidator = new MovieValidator();
        Validator<Client> clientValidator = new ClientValidator();
        Validator<Rental> rentalValidator = new RentalValidator();
        System.setProperty("username", "postgres");
        System.setProperty("password", "2233");
        Repository<UUID, Movie> movieRepo = new MovieDatabaseRepository(movieValidator);
        MovieServiceServerImplementation movieService = new MovieServiceServerImplementation(executorService, movieRepo);

        tcpServer.addHandler(MovieService.ADD_MOVIE, (request) -> {
            String movieRequest = request.getBody();
            Future<String> result =
                    movieService.addMovie(movieRequest);
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }

        });

        tcpServer.startServer();
    }


    private static Message getMessage(String header, String body) {
        return Message.builder()
                .header(header)
                .body(body)
                .build();
    }
}
