import domain.Client;
import domain.Message;
import domain.Movie;
import domain.Rental;
import domain.Validator.ClientValidator;
import domain.Validator.MovieValidator;
import domain.Validator.RentalValidator;
import domain.Validator.Validator;
import repo.Repository;
import repo.db.ClientDatabaseRepository;
import repo.db.MovieDatabaseRepository;
import service.ClientService;
import service.ClientServiceServerImplementation;
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
        Repository<UUID, Client> clientRepository = new ClientDatabaseRepository(clientValidator);
        MovieServiceServerImplementation movieService = new MovieServiceServerImplementation(executorService, movieRepo);
        ClientServiceServerImplementation clientService = new ClientServiceServerImplementation(executorService, clientRepository);

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

        tcpServer.addHandler(MovieService.DELETE_MOVIE, (request) -> {
            String id = request.getBody();
            Future<String> result =
                    movieService.deleteMovie(UUID.fromString(id));
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(MovieService.GET_MOVIE, (request) -> {
            String id = request.getBody();
            Future<String> result =
                    movieService.getMovie(UUID.fromString(id));
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(MovieService.GET_MOVIES, (request) -> {
            Future<String> result =
                    movieService.getMovies();
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(ClientService.ADD_CLIENT, (request) -> {
            String clientRequest = request.getBody();
            Future<String> result =
                    clientService.addClient(clientRequest);
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }

        });

        tcpServer.addHandler(ClientService.DELETE_CLIENT, (request) -> {
            String id = request.getBody();
            Future<String> result =
                    clientService.deleteClient(UUID.fromString(id));
            try {
                return getMessage(Message.OK, result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return getMessage(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(ClientService.GET_CLIENT, (request) -> {
            String id = request.getBody();
            Future<String> result =
                    clientService.getClient(UUID.fromString(id));
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
