package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a quantity is within a range.
 *
 * @param <T> Type of the predicate.
 */
public class QuantityWithinRangePredicate<T> implements Predicate<T> {
    public static final String DEFAULT_KEYWORD_MIN_QUANTITY = String.valueOf(0);
    public static final String DEFAULT_KEYWORD_MAX_QUANTITY = String.valueOf(Integer.MAX_VALUE);

    private final Function<T, Quantity> getter;
    private final Quantity minQuantity;
    private final Quantity maxQuantity;

    /**
     * Constructs a {@code QuantityWithinRangePredicate}.
     *
     * @param getter Function to get the {@code Quantity} to be tested.
     * @param minQuantity Min quantity to form the lower bound of the range.
     * @param maxQuantity Max quantity to form the upper bound of the range.
     */
    public QuantityWithinRangePredicate(Function<T, Quantity> getter, Quantity minQuantity, Quantity maxQuantity) {
        this.getter = getter;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    @Override
    public boolean test(T t) {
        Quantity toTest = getter.apply(t);
        return toTest.isWithinRange(minQuantity, maxQuantity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof QuantityWithinRangePredicate)) {
            return false;
        }

        QuantityWithinRangePredicate<?> otherPredicate = (QuantityWithinRangePredicate<?>) other;
        return otherPredicate.getter.equals(getter)
                && otherPredicate.minQuantity.equals(minQuantity)
                && otherPredicate.maxQuantity.equals(maxQuantity);
    }
}
