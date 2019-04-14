package config;


import domain.Movie;
import domain.Validator.MovieValidator;
import domain.Validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repo.Repository;
import repo.db.MovieDatabaseRepository;
import service.MovieService;
import service.MovieServiceServerImplementation;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ServerAppConfig {

    @Bean
    RmiServiceExporter rmiServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("MovieService");
        rmiServiceExporter.setServiceInterface(MovieService.class);
        rmiServiceExporter.setService(movieService());
        return rmiServiceExporter;
    }

    @Bean
    MovieService movieService() {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        Validator<Movie> movieValidator = new MovieValidator();
        Repository<UUID, Movie> movieRepo = new MovieDatabaseRepository(movieValidator);

        return new MovieServiceServerImplementation(executorService, movieRepo);
    }
}
