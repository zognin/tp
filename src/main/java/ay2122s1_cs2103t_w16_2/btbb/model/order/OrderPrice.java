package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

/**
 * Represents the price of an order in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class OrderPrice implements Comparable<OrderPrice> {
    public static final String MESSAGE_CONSTRAINTS =
            "Order price should only contain positive numbers up to 8 digits, with an optional 2 decimal places.";
    public static final String VALIDATION_REGEX = "^\\d{1,8}(\\.\\d{2})?$";
    private final BigDecimal orderPrice;

    /**
     * Constructs a {@code OrderPrice}.
     *
     * @param orderPrice A valid order price.
     */
    public OrderPrice(String orderPrice) {
        requireNonNull(orderPrice);
        checkArgument(isValidOrderPrice(orderPrice), MESSAGE_CONSTRAINTS);
        this.orderPrice = new BigDecimal(orderPrice);
    }

    /**
     * Returns true if a given string is a valid price.
     *
     * @param test String input to check.
     * @return True if the price is valid. False otherwise.
     */
    public static boolean isValidOrderPrice(String test) {
        return test != null && test.matches(VALIDATION_REGEX);
    }

    public Double getDoubleValue() {
        return orderPrice.doubleValue();
    }

    @Override
    public int compareTo(OrderPrice other) {
        return orderPrice.compareTo(other.orderPrice);
    }

    /**
     * Converts a OrderPrice object into its String representation.
     *
     * @return String representation of a OrderPrice object.
     */
    @Override
    public String toString() {
        return String.format("%.2f", orderPrice);
    }

    /**
     * Returns true if object and this order price are the same.
     *
     * @param other An object to compare this order price to.
     * @return True if they are the same. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderPrice // instanceof handles nulls
                && (orderPrice.compareTo(((OrderPrice) other).orderPrice) == 0)); // state check
    }

    /**
     * Implements hashcode for order price objects.
     *
     * @return The hashcode for the order price object.
     */
    @Override
    public int hashCode() {
        return orderPrice.hashCode();
    }
}
