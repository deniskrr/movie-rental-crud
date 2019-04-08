package service;

import domain.Rental;
import repo.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RentalServiceServerImplementation implements RentalService {

    private Repository<UUID, Rental> rentalRepository;

    public RentalServiceServerImplementation(Repository<UUID, Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void addRental(Rental rental) {
        this.rentalRepository.save(rental);
    }

    public void deleteRental(UUID id) {
        this.rentalRepository.delete(id);
    }


}
