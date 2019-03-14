package repo;

import domain.Rental;
import domain.Validator.Validator;

public class RentalFileRepository extends InMemoryRepository<Rental>{
    private String fileName;

    public RentalFileRepository(Validator<Rental> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
    }



}
