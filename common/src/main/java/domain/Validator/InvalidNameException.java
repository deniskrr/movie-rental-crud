package domain.Validator;

public class InvalidNameException extends ValidatorException {
    public InvalidNameException() { super("Empty name"); }
}
