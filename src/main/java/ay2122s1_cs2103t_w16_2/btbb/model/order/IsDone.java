package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the done status of an order in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class IsDone {
    public static final String MESSAGE_CONSTRAINTS = "Done Status should only be either 'yes' or 'no'.";
    private final boolean isDone;

    /**
     * Constructs a {@code isDone} object.
     *
     * @param isDone A valid isDone input, either 'yes' or 'no'.
     */
    public IsDone(String isDone) {
        requireNonNull(isDone);
        checkArgument(isValidIsDone(isDone), MESSAGE_CONSTRAINTS);
        this.isDone = isDone.equals("yes") ? true : false;
    }

    /**
     * Constructs a {@code isDone} object.
     * For internal use, does not execute check if input is valid.
     *
     * @param isDone
     */
    public IsDone(boolean isDone) {
        requireNonNull(isDone);
        this.isDone = isDone;
    }

    /**
     * Returns true if a given string is a valid isDone.
     *
     * @param test String input to check.
     * @return boolean of whether isDone is valid.
     */
    public static boolean isValidIsDone(String test) {
        return test.equals("yes") || test.equals("no");
    }

    /**
     * Converts isDone object into its String representation.
     *
     * @return String representation of isDone.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "yes";
        }
        return "no";
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Returns true if object and this isDone are the same.
     *
     * @param other object to compare this isDone to.
     * @return boolean of whether isDone and other object match.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsDone // instanceof handles nulls
                && isDone == ((IsDone) other).isDone); // state check
    }

    /**
     * Implements hashcode for isDone objects.
     *
     * @return hashcode of isDone.
     */
    @Override
    public int hashCode() {
        return isDone ? 1231 : 1237;
    }
}
