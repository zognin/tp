package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a quantity matches any of the keywords given.
 *
 * @param <T> Type of the predicate.
 */
public class ValueEqualsKeywordsPredicate<T, S> implements Predicate<T> {
    private final Function<T, S> getter;
    private final List<S> keywords;

    /**
     * Constructs a {@code QuantityEqualsKeywordsPredicate}.
     *
     * @param getter Function to get the {@code Quantity} to be tested.
     * @param keywords List of quantity keywords to be checked against.
     */
    public ValueEqualsKeywordsPredicate(Function<T, S> getter, List<S> keywords) {
        this.getter = getter;
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        S toTest = getter.apply(t);
        return keywords.stream().anyMatch(toTest::equals);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ValueEqualsKeywordsPredicate) // instanceof handles nulls
                && keywords.equals(((ValueEqualsKeywordsPredicate<?, ?>) other).keywords); // state check
    }
}
