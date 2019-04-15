package service;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ClientServiceClientImplementation implements ClientService
{
    private ExecutorService executorService;

    public ClientServiceClientImplementation(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> addClient(String clientParams) {
        return null;
    }

    @Override
    public Future<String> deleteClient(UUID uid) {
        return null;
    }

    @Override
    public Future<String> getClient(UUID uid) {
        return null;
    }
}