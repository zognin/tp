package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.List;
import java.util.function.Function;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.StringContainsKeywordsPredicate;

/**
 * A utility class for predicates.
 */
public class PredicateUtil {
    /**
     * Parses {@code userInput} into a {@code StringContainsKeywordsPredicate<T>}.
     */
    public static <T> StringContainsKeywordsPredicate<T> makeStringContainsKeywordsPredicate(String input,
                                                                               Function<T, ?> getter) {
        List<String> keywords = List.of(input.split("\\s+"));
        return new StringContainsKeywordsPredicate<>(getter, keywords);
    }
}
