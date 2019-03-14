package controller;

import domain.Client;
import domain.Validator.ValidatorException;
import repo.IRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientController {
    private IRepository<UUID, Client> clientRepository;

    public ClientController(IRepository<UUID, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    public Set<Client> getClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

}
