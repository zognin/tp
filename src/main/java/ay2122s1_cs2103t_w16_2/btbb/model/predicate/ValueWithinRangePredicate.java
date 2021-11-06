package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a value is within a range.
 * The value must implement the comparable interface.
 *
 * @param <T> Type of the predicate.
 * @param <S> Type of the values to be tested.
 */
public class ValueWithinRangePredicate<T, S extends Comparable<S>> implements Predicate<T> {
    public static final String MESSAGE_CONSTRAINTS = "Please provide a valid range where FROM is smaller than"
            + " or equal to TO.";

    private final Function<T, S> getter;
    private final S minValue;
    private final S maxValue;

    /**
     * Constructs a {@code ValueWithinRangePredicate}.
     *
     * @param getter Function to get the value to be tested.
     * @param minValue Min value to form the lower bound of the range.
     * @param maxValue Max value to form the upper bound of the range.
     */
    public ValueWithinRangePredicate(Function<T, S> getter, S minValue, S maxValue) {
        this.getter = getter;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean test(T t) {
        S toTest = getter.apply(t);
        return toTest.compareTo(minValue) >= 0 && toTest.compareTo(maxValue) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ValueWithinRangePredicate)) {
            return false;
        }

        ValueWithinRangePredicate<?, ?> otherPredicate = (ValueWithinRangePredicate<?, ?>) other;
        return otherPredicate.minValue.equals(minValue)
                && otherPredicate.maxValue.equals(maxValue);
    }
}
