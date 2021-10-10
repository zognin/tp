package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the price of a recipe in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Price {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers, it should be positive "
                    + "and the largest acceptable price is " + Float.MAX_VALUE + ".";
    private final float price;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        this.price = Float.parseFloat(price);
    }

    /**
     * Returns true if a given string is a valid price.
     *
     * @param test String input to check.
     * @return True if the price is valid. False otherwise.
     */
    public static boolean isValidPrice(String test) {
        if (test == null) {
            return  false;
        }

        try {
            float price = Float.parseFloat(test);
            return price > 0 && price <= Float.MAX_VALUE;
        } catch (NumberFormatException e) {
            return false;
        }
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
                && price == ((Price) other).price); // state check
    }

    /**
     * Implements hashcode for price objects.
     *
     * @return The hashcode for the price object.
     */
    @Override
    public int hashCode() {
        return Float.hashCode(price);
    }
}
