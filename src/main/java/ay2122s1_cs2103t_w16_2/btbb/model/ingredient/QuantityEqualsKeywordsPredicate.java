package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a quantity matches any of the keywords given.
 *
 * @param <T> Type of the predicate.
 */
public class QuantityEqualsKeywordsPredicate<T> implements Predicate<T> {
    private final Function<T, Quantity> getter;
    private final List<Quantity> keywords;

    /**
     * Constructs a {@code QuantityEqualsKeywordsPredicate}.
     *
     * @param getter Function to get the {@code Quantity} to be tested.
     * @param keywords List of quantity keywords to be checked against.
     */
    public QuantityEqualsKeywordsPredicate(Function<T, Quantity> getter, List<Quantity> keywords) {
        this.getter = getter;
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        Quantity toTest = getter.apply(t);
        return keywords.stream().anyMatch(toTest::equals);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuantityEqualsKeywordsPredicate) // instanceof handles nulls
                && keywords.equals(((QuantityEqualsKeywordsPredicate<?>) other).keywords); // state check
    }
}
