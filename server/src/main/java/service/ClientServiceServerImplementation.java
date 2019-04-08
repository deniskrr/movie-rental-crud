package controller;

import domain.Client;
import domain.Validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientController {
    private Repository<UUID, Client> clientRepository;

    @Autowired
    public ClientController(Repository<UUID, Client> clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) throws ValidatorException {
        clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
//        clientRepository.delete(id);
    }


}
