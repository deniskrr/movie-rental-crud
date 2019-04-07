package service;

import java.util.UUID;
import java.util.concurrent.Future;

public interface ClientService extends Service {
    String ADD_CLIENT = "addClient";
    String DELETE_CLIENT = "deleteClient";
    String GET_CLIENT = "getClient";

    Future<String> addClient (String clientParams);

    Future<String> deleteClient (UUID uid);

    Future<String> getClient (UUID uid);
}
