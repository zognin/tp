package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the quantity of an Ingredient in btbb.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers, it should be positive "
                    + "and the largest acceptable quantity is 40000.";
    private final int quantity;

    /**
     * Constructs a {@code Quantity} with a default value of 1.
     */
    public Quantity() {
        quantity = 1;
    }

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Returns true if a given string is a valid quantity.
     *
     * @param test String input to check.
     * @return boolean of whether name is valid.
     */
    public static boolean isValidQuantity(String test) {
        try {
            int quantity = Integer.parseInt(test);
            return quantity >= 0 && quantity <= 40000;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    /**
     * Returns a new {@code Quantity} object with its quantity reduced by amount * multiplier.
     *
     * @param amount The amount to decrease.
     * @param multiplier The multiplier.
     * @return A new {@code Quantity} object.
     */
    public Quantity minusQuantityBy(Quantity amount, Quantity multiplier) {
        return new Quantity(Integer.toString(Math.max(quantity - amount.quantity * multiplier.quantity, 0)));
    }

    /**
     * Converts Quantity object into its String representation.
     *
     * @return String representation of quantity.
     */
    @Override
    public String toString() {
        return Integer.toString(quantity);
    }

    /**
     * Returns true if object and this quantity are the same.
     *
     * @param other object to compare this quantity to.
     * @return boolean of whether quantity and other object match.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity == ((Quantity) other).quantity); // state check
    }

    /**
     * Implements hashcode for quantity objects.
     *
     * @return hashcode of quantity.
     */
    @Override
    public int hashCode() {
        return quantity;
    }
}
