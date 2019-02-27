package repo;

import domain.Movie;
import domain.Validator.MovieValidator;

class MovieRepository extends InMemoryRepository<Movie> {

    public MovieRepository(MovieValidator validator) {
        super(validator);
    }
}