package service;

import domain.Message;
import tcp.TcpClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MovieServiceClientImplementation implements MovieService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public MovieServiceClientImplementation(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> addMovie(String movieParameters) {
        return CompletableFuture.supplyAsync(() -> {

            Message request = Message.builder()
                    .header(MovieService.ADD_MOVIE)
                    .body(movieParameters)
                    .build();

            Message response = tcpClient.sendAndReceive(request);

            String result = response.getBody();


            return result;
        });
    }
}
