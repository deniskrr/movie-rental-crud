package controller;

import domain.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repo.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentalController {

    private Repository<UUID, Rental> rentalRepository;

    @Autowired
    public RentalController(Repository<UUID, Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void addRental(Rental rental) {
        this.rentalRepository.save(rental);
    }

    public void deleteRental(UUID id) {
//        this.rentalRepository.delete(id);
    }


}
