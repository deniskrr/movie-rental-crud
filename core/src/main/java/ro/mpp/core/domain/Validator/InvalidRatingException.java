package ro.mpp.core.domain.Validator;

public class InvalidRatingException extends ValidatorException {
    public InvalidRatingException() { super("Invalid rating"); }
}
