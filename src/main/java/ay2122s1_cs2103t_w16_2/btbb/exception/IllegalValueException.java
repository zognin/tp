package ay2122s1_cs2103t_w16_2.btbb.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * @param message should contain relevant information on the failed constraint(s)
     * @param cause of the main exception
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
