package service;

import domain.Message;
import tcp.TcpClient;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientServiceClientImplementation implements ClientService
{
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientServiceClientImplementation(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> addClient(String clientParams) {
        return CompletableFuture.supplyAsync(() -> {

            Message request = Message.builder()
                    .header(ClientService.ADD_CLIENT)
                    .body(clientParams)
                    .build();

            Message response = tcpClient.sendAndReceive(request);
            String result = response.getBody();
            return result;
        });
    }

    @Override
    public Future<String> deleteClient(UUID uid) {
        return CompletableFuture.supplyAsync(() -> {

            Message request = Message.builder()
                    .header(ClientService.DELETE_CLIENT)
                    .body(uid.toString())
                    .build();

            Message response = tcpClient.sendAndReceive(request);
            String result = response.getBody();
            return result;
        });
    }

    @Override
    public Future<String> getClient(UUID uid) {
        return CompletableFuture.supplyAsync(() -> {

            Message request = Message.builder()
                    .header(ClientService.GET_CLIENT)
                    .body(uid.toString())
                    .build();

            Message response = tcpClient.sendAndReceive(request);
            String result = response.getBody();
            return result;
        });
    }
}