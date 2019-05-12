package ro.mpp.core.domain.Validator;

public class RentalException extends RuntimeException{

    public RentalException(String message) {
        super(message);
    }

    public RentalException(String message, Throwable cause) {
        super(message, cause);
    }

    public RentalException(Throwable cause) {
        super(cause);
    }
}