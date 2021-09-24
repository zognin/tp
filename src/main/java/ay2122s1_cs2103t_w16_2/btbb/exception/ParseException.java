package ay2122s1_cs2103t_w16_2.btbb.exception;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
