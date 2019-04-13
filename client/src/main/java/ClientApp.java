import service.*;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        MovieService movieService = new MovieServiceClientImplementation(executorService);
        ClientService clientService = new ClientServiceClientImplementation(executorService);

        Console console = new Console(movieService, clientService);
        console.run();

        executorService.shutdown();

        System.out.println("Client exit");


    }
}
