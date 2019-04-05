package service;


import domain.Client;
import domain.Validator.ValidatorException;
import repo.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientServiceServerImplementation implements ClientService {
    private Repository<UUID, Client> clientRepository;

    public ClientServiceServerImplementation(Repository<UUID, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.delete(id);
    }

    public Client getClient(UUID id) {
        if (clientRepository.findOne(id).isPresent()) {
            return clientRepository.findOne(id).get();
        }
        return null;
    }

    public List<Client> getClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
