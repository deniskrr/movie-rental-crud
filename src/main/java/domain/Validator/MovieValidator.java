package domain.Validator;

import domain.Movie;

public class MovieValidator implements Validator<Movie> {
    @Override
    public void validate(Movie entity) throws ValidatorException {
        if (entity.getTitle().trim().equals("")) {
            throw new ValidatorException("Empty title");
        }
        if (entity.getRating() > 10 || entity.getRating() < 0) {
            throw new ValidatorException("Invalid rating");
        }
        if (entity.getYear() < 0 || entity.getYear() > 2019) {
            throw new ValidatorException("Invalid year");
        }
        if (entity.getGenre().trim().equals("")) {
            throw new ValidatorException("Empty genre");
        }
    }
}
