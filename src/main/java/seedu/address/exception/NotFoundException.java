package seedu.address.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String notFoundItem) {
        super("The specified " + notFoundItem + " cannot be found.");
    }
}
