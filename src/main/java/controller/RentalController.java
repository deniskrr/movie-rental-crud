package controller;

import domain.Client;
import domain.Movie;
import domain.Rental;
import repo.IRepository;

import java.util.UUID;

public class RentalController {

    private IRepository<UUID, Rental> rentalRepository;

    public RentalController(IRepository<UUID, Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void addRental(Rental rental) {
        this.rentalRepository.save(rental);
    }

}
