package ro.mpp.core.domain.Validator;

public class InvalidGenreException extends ValidatorException {
    public InvalidGenreException() { super("Empty genre"); }
}
