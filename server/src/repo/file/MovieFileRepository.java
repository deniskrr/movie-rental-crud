package repo.file;

import domain.Movie;
import domain.Validator.Validator;
import domain.Validator.ValidatorException;
import repo.InMemoryRepository;

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

public class MovieFileRepository extends InMemoryRepository<Movie> {

    private String fileName;

    public MovieFileRepository(Validator<Movie> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        this.loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                String title = items.get(1);
                double rating = Double.parseDouble(items.get((2)));
                int year = Integer.parseInt(items.get(3));
                String genre = items.get(4);

                Movie movie = new Movie(title, rating, year,genre);
                movie.setId(UUID.fromString(items.get(0)));
                try {
                    super.save(movie);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException {
        Optional<Movie> optional = super.save(entity);
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
                            entity.getId() + "," + entity.getTitle() + "," + entity.getRating() + "," + entity.getYear() + ","
                                    + entity.getGenre());
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