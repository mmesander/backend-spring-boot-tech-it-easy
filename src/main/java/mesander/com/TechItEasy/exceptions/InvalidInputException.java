package mesander.com.TechItEasy.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
