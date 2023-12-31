package ua.com.alevel.exceptions;

public class EntityExistException extends RuntimeException {

    private final String message;

    public EntityExistException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
