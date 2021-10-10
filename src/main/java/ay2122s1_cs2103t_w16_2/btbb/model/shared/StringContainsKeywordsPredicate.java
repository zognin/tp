package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests that a string field matches any of the keywords given.
 *
 * @param <T> Type of the predicate.
 */
public class StringContainsKeywordsPredicate<T> implements Predicate<T> {
    private final Function<T, ?> getter;
    private final List<String> keywords;

    /**
     * Constructs a {@code GenericStringPredicate}.
     *
     * @param getter Function to get the string to be tested.
     * @param keywords List of keywords to be checked against.
     */
    public StringContainsKeywordsPredicate(Function<T, ?> getter, List<String> keywords) {
        this.getter = getter;
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        String toTest = getter.apply(t).toString().toLowerCase(Locale.ROOT);
        return keywords.stream().anyMatch(keyword -> toTest.contains(keyword.toLowerCase(Locale.ROOT)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StringContainsKeywordsPredicate) // instanceof handles nulls
                && keywords.equals(((StringContainsKeywordsPredicate<?>) other).keywords); // state check
    }
}
