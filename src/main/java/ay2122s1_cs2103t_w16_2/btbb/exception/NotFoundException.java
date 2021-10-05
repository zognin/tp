package ay2122s1_cs2103t_w16_2.btbb.exception;

public class NotFoundException extends Exception {
    public static final String NOT_FOUND_EXCEPTION_MESSAGE_FORMAT = "The specified %s cannot be found.";

    public NotFoundException(String notFoundItem) {
        super(String.format(NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, notFoundItem));
    }
}
