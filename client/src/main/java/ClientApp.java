import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.*;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("config"
                );

        MovieServiceClientImplementation movieService = (MovieServiceClientImplementation)
                context.getBean("clientImplementation");

        ClientServiceClientImplementation clientService = new ClientServiceClientImplementation(executorService);

        Console ui = new Console(movieService, clientService);
        ui.run();
    }
}
