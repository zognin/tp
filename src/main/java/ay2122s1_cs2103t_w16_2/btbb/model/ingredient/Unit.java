package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

public class Unit {
    public static final String MESSAGE_CONSTRAINTS =
            "Units should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    private final String unit;

    /**
     * Constructs a {@code Unit}.
     *
     * @param unit A valid unit.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(isValidUnit(unit), MESSAGE_CONSTRAINTS);
        this.unit = unit;
    }

    /**
     * Returns true if a given string is a valid unit.
     */
    public static boolean isValidUnit(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return unit;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && unit.equals(((Unit) other).unit)); // state check
    }

    @Override
    public int hashCode() {
        return unit.hashCode();
    }
}
