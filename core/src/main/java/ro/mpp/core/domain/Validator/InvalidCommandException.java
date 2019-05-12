package ro.mpp.core.domain.Validator;

public class InvalidCommandException extends ValidatorException {
    public InvalidCommandException() { super("Invalid command."); }
}
