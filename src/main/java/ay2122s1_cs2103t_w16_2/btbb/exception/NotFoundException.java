package ay2122s1_cs2103t_w16_2.btbb.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String notFoundItem) {
        super("The specified " + notFoundItem + " cannot be found.");
    }
}
