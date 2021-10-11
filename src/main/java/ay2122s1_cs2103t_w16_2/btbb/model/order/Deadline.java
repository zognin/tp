package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents the deadline of an order in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline {
    private static final String INPUT_FORMAT = "dd-MM-uuuu HHmm";
    public static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(INPUT_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
    private static final String OUTPUT_FORMAT = "MMM d uuuu h:mm a";
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT)
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String MESSAGE_CONSTRAINTS = "Deadline should be in "
            + INPUT_FORMAT + " format and should occur in the future.";

    private final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidInternalDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDateTime.parse(deadline, INPUT_DATETIME_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid deadline.
     *
     * @param test String input to check.
     * @return True if the deadline is valid. False otherwise.
     */
    public static boolean isValidDeadline(String test) {
        if (test == null) {
            return false;
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, INPUT_DATETIME_FORMATTER);
            return dateTime.isAfter(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid deadline.
     * For internal use where there is a wider definition of a valid deadline.
     *
     * @param test String input to check.
     * @return True if the deadline is valid. False otherwise.
     */
    public static boolean isValidInternalDeadline(String test) {
        if (test == null) {
            return false;
        }

        try {
            LocalDateTime.parse(test, INPUT_DATETIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Converts a Deadline object into its String representation.
     *
     * @return String representation of a deadline object.
     */
    @Override
    public String toString() {
        return deadline.format(OUTPUT_DATETIME_FORMATTER);
    }

    /**
     * Converts a Deadline object into its JSON storage String representation.
     *
     * @return JSON String representation of a deadline object.
     */
    public String toJsonStorageString() {
        return deadline.format(INPUT_DATETIME_FORMATTER);
    }

    /**
     * Returns true if object and this deadline are the same.
     *
     * @param other An object to compare this deadline to.
     * @return True if they are the same. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    /**
     * Implements hashcode for deadline objects.
     *
     * @return The hashcode for the deadline object.
     */
    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
