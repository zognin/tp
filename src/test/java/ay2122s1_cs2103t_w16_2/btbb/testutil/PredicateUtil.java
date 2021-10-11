package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.StringContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.ValueEqualsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.ValueWithinRangePredicate;

/**
 * A utility class for predicates.
 */
public class PredicateUtil {
    /**
     * Takes a variable number of objects, converts them to string and combines them to one space separated String.
     *
     * @param objects Objects to be converted to string.
     * @return String separating each object's string representation by a space.
     */
    public static String makeSpaceSeparatedStringKeywords(Object... objects) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(objects).forEach(keyword -> {
            sb.append(keyword.toString());
            sb.append(" ");
        });
        return sb.toString().trim();
    }

    /**
     * Makes a {@code ValueEqualsKeywordsPredicate} for quantity values.
     *
     * @param input String input to get keywords from.
     * @param getter Getter to get item to test predicate with.
     * @param <T> Type of predicate.
     * @return {@code ValueEqualsKeywordsPredicate} for quantity values.
     */
    public static <T> ValueEqualsKeywordsPredicate<T, Quantity> makeQuantityEqualsKeywordsPredicate(String input,
            Function<T, Quantity> getter) {
        List<String> keywords = List.of(input.split("\\s+"));
        List<Quantity> quantityKeywords = new ArrayList<>();
        for (String keyword : keywords) {
            quantityKeywords.add(new Quantity(keyword));
        }
        return new ValueEqualsKeywordsPredicate<>(getter, quantityKeywords);
    }

    /**
     * Makes a {@code ValueWithinRangePredicate} for quantity values.
     *
     * @param minQuantityKeyword Minimum quantity to form the lower bound of the range.
     * @param maxQuantityKeyword Maximum quantity to form the upper bound of the range.
     * @param getter Getter to get item to test predicate with.
     * @param <T> Type of predicate.
     * @return {@code ValueWithinRangePredicate} for quantity values.
     */
    public static <T> ValueWithinRangePredicate<T, Quantity> makeQuantityWithinRangePredicate(
            String minQuantityKeyword,
            String maxQuantityKeyword,
            Function<T, Quantity> getter) {
        return new ValueWithinRangePredicate<>(getter,
                new Quantity(minQuantityKeyword),
                new Quantity(maxQuantityKeyword));
    }

    /**
     * Parses {@code userInput} into a {@code StringContainsKeywordsPredicate<T>}.
     */
    public static <T> StringContainsKeywordsPredicate<T> makeStringContainsKeywordsPredicate(String input,
            Function<T, ?> getter) {
        List<String> keywords = List.of(input.split("\\s+"));
        return new StringContainsKeywordsPredicate<>(getter, keywords);
    }
}
