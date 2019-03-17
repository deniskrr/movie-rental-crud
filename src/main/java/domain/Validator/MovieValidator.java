package domain.Validator;

import domain.Movie;

public class MovieValidator implements Validator<Movie> {
    @Override
    public void validate(Movie entity) throws ValidatorException {
        if (entity.getTitle().trim().equals("")) {
            throw new InvalidTitleException();
        }
        if (entity.getRating() > 10 || entity.getRating() < 0) {
            throw new InvalidRatingException();
        }
        if (entity.getYear() < 0 || entity.getYear() > 2019) {
            throw new InvalidYearException();
        }
        if (entity.getGenre().trim().equals("")) {
            throw new InvalidGenreException();
        }
    }
}
