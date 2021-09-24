package ay2122s1_cs2103t_w16_2.btbb.exception;

/**
 * Represents an error during conversion of data from one format to another
 */
public class DataConversionException extends Exception {
    public DataConversionException(Exception cause) {
        super(cause);
    }
}
