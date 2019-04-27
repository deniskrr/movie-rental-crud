package domain.Validator;

public class InvalidTitleException extends ValidatorException {
    public InvalidTitleException() { super("Empty title"); }

}
