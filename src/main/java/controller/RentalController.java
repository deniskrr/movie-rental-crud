package controller;

import domain.Client;
import domain.Movie;
import domain.Rental;
import repo.IRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RentalController {

    private IRepository<UUID, Rental> rentalRepository;

    public RentalController(IRepository<UUID, Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void addRental(Rental rental) {
        this.rentalRepository.save(rental);
    }

    public void deleteRental(UUID id) {
        this.rentalRepository.delete(id);
    }

    public List<Rental> getRentals() {
        return StreamSupport.stream(rentalRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public UUID getMostRentedMovie() {
        return getRentals().stream()
                .collect(Collectors.groupingBy(Rental::getMovieID, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }
}
