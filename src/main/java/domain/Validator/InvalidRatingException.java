package domain.Validator;
import domain.Validator.ValidatorException;
import domain.Movie;

public class InvalidRatingException extends ValidatorException {
    public InvalidRatingException() { super("Invalid rating"); }
}
