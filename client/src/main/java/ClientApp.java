import service.MovieService;
import service.MovieServiceClientImplementation;
import service.Service;
import tcp.TcpClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(Service.SERVER_HOST, Service.SERVER_PORT);

        MovieService movieService = new MovieServiceClientImplementation(executorService, tcpClient);

        try {
            movieService.addMovie("Deniiiis,10,1998,Action").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println("Client exit");


    }
}
