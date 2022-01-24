package CustomExceptions;

public class OutOfRangeException extends RuntimeException{
    public OutOfRangeException() {
    }

    public OutOfRangeException(String message) {
        super(message);
    }

    public OutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
