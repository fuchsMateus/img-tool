package fuchs.exceptions;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException() {
        super("Invalid command action encountered.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
