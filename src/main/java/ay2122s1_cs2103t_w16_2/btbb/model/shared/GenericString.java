package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.Locale;

/**
 * Represents a Generic String in the address book, that is reusable.
 * Guarantees: immutable; is valid as declared in {@link #isValidGenericString(String)}
 */
public class GenericString implements Comparable<GenericString> {
    private static final String MESSAGE_CONSTRAINTS =
            " should only contain alphanumeric characters and spaces, and it should not be blank.\n"
            + "The maximum allowed length is 50 characters including spaces.";

    private static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]{1,50}$";

    private final String genericString;

    /**
     * Constructs a {@code GenericString}.
     *
     * @param genericString A valid generic string.
     */
    public GenericString(String genericString) {
        requireNonNull(genericString);
        checkArgument(isValidGenericString(genericString), getMessageConstraints("Generic String"));
        this.genericString = genericString;
    }

    /**
     * Returns true if a given string is a valid Generic String.
     *
     * @param test String input to test.
     * @return true if test is valid.
     */
    public static boolean isValidGenericString(String test) {
        return test.trim().matches(VALIDATION_REGEX);
    }

    /**
     * Returns the Message Constraints for different classes that extend GenericString.
     *
     * @param attributeName name of class that extends Generic String.
     * @return Message Constraint.
     */
    public static String getMessageConstraints(String attributeName) {
        return attributeName + MESSAGE_CONSTRAINTS;
    }

    /**
     * Returns true if both generic strings have the same lower case string.
     * This defines a weaker notion of equality between two generic strings.
     *
     * @param otherGenericString to compare this generic string to.
     * @return boolean of whether the two generic strings match.
     */
    public boolean isSameGenericString(GenericString otherGenericString) {
        if (otherGenericString == this) {
            return true;
        }

        return otherGenericString != null
                && otherGenericString.genericString.toLowerCase(Locale.ROOT)
                .equals(genericString.toLowerCase(Locale.ROOT));
    }

    @Override
    public int compareTo(GenericString other) {
        return genericString.compareTo(other.genericString);
    }

    @Override
    public String toString() {
        return genericString;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenericString // instanceof handles nulls
                && genericString.equals(((GenericString) other).genericString)); // state check
    }

    @Override
    public int hashCode() {
        return genericString.hashCode();
    }
}
