//package service;
//
//
//import domain.Client;
//import domain.Validator.ValidatorException;
//import repo.Repository;
//
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Future;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//public class ClientServiceServerImplementation implements ClientService {
//    private Repository<UUID, Client> clientRepository;
//    private ExecutorService executorService;
//
//    public ClientServiceServerImplementation(ExecutorService executorService, Repository<UUID, Client> clientRepository) {
//        this.executorService = executorService;
//        this.clientRepository = clientRepository;
//    }
//
//    public Future<String> addClient(String clientParams) throws ValidatorException {
//        String[] clientParamsArray = clientParams.split(",");
//        Client client = new Client(clientParamsArray[0],
//                clientParamsArray[1],
//                Integer.valueOf(clientParamsArray[2]));
//        return CompletableFuture.supplyAsync(() -> clientRepository.save(client), executorService)
//                .thenApply((optional) -> {
//                    if (optional.isPresent()) {
//                        return "Client was added to the repository.";
//                    } else {
//                        return "Client was NOT added to the repository.";
//                    }
//                });
//
//    }
//
//    public Future<String> deleteClient(UUID id) {
//        return CompletableFuture.supplyAsync(() -> clientRepository.delete(id), executorService)
//                .thenApply((optional) -> {
//                    if (optional.isPresent()) {
//                        if (optional.get() == true) {
//                            return "Client was deleted from the repository";
//                        } else {
//                            return "Client was not found in the repository";
//                        }
//                    } else {
//                        return "Client was NOT deleted from the repository";
//                    }
//                });
//    }
//
//
//    public Future<String> getClient(UUID id) {
//        return CompletableFuture.supplyAsync(() -> clientRepository.findOne(id), executorService)
//                .thenApply((optional) -> {
//                    if (optional.isPresent()) {
//                        return optional.get().toString();
//                    } else {
//                        return "Client not found";
//                    }
//                });
//    }
//
//
//}
