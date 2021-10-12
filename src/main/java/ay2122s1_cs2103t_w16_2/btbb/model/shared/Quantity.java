package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the quantity of an Ingredient in btbb.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Quantity implements Comparable<Quantity> {
    public static final int MIN_QUANTITY = 0;
    public static final int MAX_QUANTITY = 40000;
    public static final String DEFAULT_MIN_QUANTITY_STRING = String.valueOf(0);
    public static final String DEFAULT_MAX_QUANTITY_STRING = String.valueOf(MAX_QUANTITY);
    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should only contain numbers, it should be positive "
                    + "and the largest acceptable quantity is 40000.";

    public static final String MESSAGE_INTERNAL_CONSTRAINTS = "Quantity should only contain numbers, "
            + "it should be non-negative and the largest acceptable quantity is 40000.";
    private final int quantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity number.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidInternalQuantity(quantity), MESSAGE_INTERNAL_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Returns true if a given string is a valid quantity.
     *
     * @param test String input to check.
     * @return boolean of whether quantity is valid.
     */
    public static boolean isValidQuantity(String test) {
        try {
            int quantity = Integer.parseInt(test);
            return quantity > MIN_QUANTITY && quantity <= MAX_QUANTITY;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid internal quantity.
     * For internal use there is a wider definition of a valid quantity.
     *
     * @param test String input to check.
     * @return boolean of whether quantity is valid.
     */
    public static boolean isValidInternalQuantity(String test) {
        try {
            int quantity = Integer.parseInt(test);
            return quantity >= MIN_QUANTITY && quantity <= MAX_QUANTITY;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    /**
     * Returns a new {@code Quantity} object with its quantity increased by amount * multiplier.
     *
     * @param amount The amount to increase.
     * @param multiplier The multiplier.
     * @return A new {@code Quantity} object.
     */
    public Quantity addQuantityBy(Quantity amount, Quantity multiplier) {
        return new Quantity(Integer.toString(Math.min(quantity + amount.quantity * multiplier.quantity, MAX_QUANTITY)));
    }

    /**
     * Returns a new {@code Quantity} object with its quantity reduced by amount * multiplier.
     *
     * @param amount The amount to decrease.
     * @param multiplier The multiplier.
     * @return A new {@code Quantity} object.
     */
    public Quantity minusQuantityBy(Quantity amount, Quantity multiplier) {
        return new Quantity(Integer.toString(Math.max(quantity - amount.quantity * multiplier.quantity, MIN_QUANTITY)));
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

    @Override
    public int compareTo(Quantity otherQuantity) {
        return this.quantity - otherQuantity.quantity;
    }
}
