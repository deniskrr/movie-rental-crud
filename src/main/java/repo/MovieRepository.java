package repo;

import domain.Movie;
import domain.Validator.Validator;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class MovieRepository extends InMemoryRepository<Movie> {

    public MovieRepository(Validator<Movie> validator) {
        super(validator);
    }

    public Collection<Movie> getMovies() {
        return this.entities.values();
    }

}