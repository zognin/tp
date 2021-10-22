package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

/**
 * Represents the price of a recipe in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Price implements Comparable<Price> {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain positive numbers up to 8 digits, with an optional 2 decimal places.";
    public static final String VALIDATION_REGEX = "^\\d{1,8}(\\.\\d{2})?$";
    private final BigDecimal price;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = new BigDecimal(price);
    }

    /**
     * Returns true if a given string is a valid price.
     *
     * @param test String input to check.
     * @return True if the price is valid. False otherwise.
     */
    public static boolean isValidPrice(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    public Double doubleValue() {
        return price.doubleValue();
    }

    @Override
    public int compareTo(Price other) {
        return price.compareTo(other.price);
    }

    /**
     * Converts a Price object into its String representation.
     *
     * @return String representation of a price object.
     */
    @Override
    public String toString() {
        return String.format("%.2f", price);
    }

    /**
     * Returns true if object and this price are the same.
     *
     * @param other An object to compare this price to.
     * @return True if they are the same. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Price // instanceof handles nulls
                && (price.compareTo(((Price) other).price) == 0)); // state check
    }

    /**
     * Implements hashcode for price objects.
     *
     * @return The hashcode for the price object.
     */
    @Override
    public int hashCode() {
        return price.hashCode();
    }
}
