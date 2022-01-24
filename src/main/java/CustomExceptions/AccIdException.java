package CustomExceptions;

public class AccIdException extends RuntimeException{
    public AccIdException() {
    }

    public AccIdException(String message) {
        super(message);
    }

    public AccIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
