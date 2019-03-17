package domain.Validator;
import domain.Validator.ValidatorException;
import domain.Movie;

public class InvalidTitleException extends ValidatorException {
    public InvalidTitleException() { super("Empty title"); }

}
