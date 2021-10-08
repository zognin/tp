package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Generic String in the address book, that is reusable.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenericString(String)}
 */
public class GenericString {
    public static final String MESSAGE_CONSTRAINTS =
            "Generic Strings should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
    */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String genericString;

    /**
     * Constructs a {@code GenericString}.
     *
     * @param genericString A valid generic string.
     */
    public GenericString(String genericString) {
        requireNonNull(genericString);
        checkArgument(isValidGenericString(genericString), MESSAGE_CONSTRAINTS);
        this.genericString = genericString;
    }

    /**
     * Returns true if a given string is a valid Generic String.
     *
     * @param test String input to test.
     * @return true if test is valid.
     */
    public static boolean isValidGenericString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return genericString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString // instanceof handles nulls
                && genericString.equals(((GenericString) other).genericString)); // state check
    }

    @Override
    public int hashCode() {
        return genericString.hashCode();
    }
}
