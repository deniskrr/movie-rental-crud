import service.*;
import tcp.TcpClient;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(Service.SERVER_HOST, Service.SERVER_PORT);

        MovieService movieService = new MovieServiceClientImplementation(executorService, tcpClient);
        ClientService clientService = new ClientServiceClientImplementation(executorService, tcpClient);

        Console console = new Console(movieService, clientService);
        console.run();

        executorService.shutdown();

        System.out.println("Client exit");


    }
}
