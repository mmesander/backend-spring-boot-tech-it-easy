package mesander.com.TechItEasy.exceptions;

public class IndexOutOfBoundsException extends RuntimeException {
    public IndexOutOfBoundsException() {
        super();
    }

    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
