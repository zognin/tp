package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests if a value is in a list of values.
 *
 * @param <T> Type of the predicate.
 * @param <S> Type of the values to be tested.
 */
public class ValueInListPredicate<T, S> implements Predicate<T> {
    private final Function<T, S> getter;
    private final List<S> values;

    /**
     * Constructs a {@code ValueInListPredicate}.
     *
     * @param getter Function to get the value to be tested.
     * @param values List of values to be checked against.
     */
    public ValueInListPredicate(Function<T, S> getter, List<S> values) {
        this.getter = getter;
        this.values = values;
    }

    @Override
    public boolean test(T t) {
        return values.contains(getter.apply(t));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ValueInListPredicate) // instanceof handles nulls
                && values.equals(((ValueInListPredicate<?, ?>) other).values); // state check
    }
}
