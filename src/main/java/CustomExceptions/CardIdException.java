package CustomExceptions;

public class CardIdException extends RuntimeException{
    public CardIdException() {
    }

    public CardIdException(String message) {
        super(message);
    }

    public CardIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
