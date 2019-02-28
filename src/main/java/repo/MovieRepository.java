package repo;

import domain.Movie;
import domain.Validator.Validator;

public class MovieRepository extends InMemoryRepository<Movie> {

    public MovieRepository(Validator<Movie> validator) {
        super(validator);
    }
}