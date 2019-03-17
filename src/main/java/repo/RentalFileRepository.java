package repo;

import domain.Movie;
import domain.Rental;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RentalFileRepository extends InMemoryRepository<Rental>{
    private String fileName;

    public RentalFileRepository(Validator<Rental> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        this.loadData();
    }


    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                String clientID = items.get(1);
                String movieID = items.get(2);

                Rental rental = new Rental(UUID.fromString(clientID), UUID.fromString(movieID));
                rental.setId(UUID.fromString(items.get(0)));
                try {
                    super.save(rental);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Rental> save(Rental entity) throws ValidatorException {
        Optional<Rental> optional = super.save(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile();
        return Optional.empty();
    }

    private void saveToFile() {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            super.findAll().forEach(entity -> {
                try {
                    bufferedWriter.write(
                            entity.getId() + "," + entity.getClientID() + "," + entity.getMovieID());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
