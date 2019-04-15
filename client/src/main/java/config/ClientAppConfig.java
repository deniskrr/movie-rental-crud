package config;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.MovieService;
import service.MovieServiceClientImplementation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientAppConfig {

    @Bean
    MovieServiceClientImplementation clientImplementation() {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        return new MovieServiceClientImplementation(executorService);
    }

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(MovieService.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/MovieService");
        return rmiProxyFactoryBean;
    }

}
