package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.Prefix;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;

/**
 * Tests that all given predicates match.
 *
 * @param <T> Type of the predicate.
 */
public class PredicateCollection<T> implements Predicate<T> {
    private final List<Predicate<T>> predicates = new ArrayList<>();

    private boolean hasSamePredicates(List<Predicate<T>> otherPredicates) {
        return predicates.containsAll(otherPredicates) && otherPredicates.containsAll(predicates);
    }

    /**
     * Adds a predicate to the list of predicates to test against.
     *
     * @param predicate The Predicate to add to the list.
     */
    public void addPredicate(Predicate<T> predicate) {
        predicates.add(predicate);
    }

    /**
     * Adds a {@code ValueEqualsKeywordsPredicate} for quantity values to the list of predicates.
     *
     * @param prefix Prefix of keyword.
     * @param argMultimap ArgumentMultimap to get the value associated with a prefix.
     * @param getter Function to get the quantity to be tested.
     * @throws ParseException if the given keywords is invalid.
     */
    public void addQuantityEqualsKeywordsPredicate(Prefix prefix, ArgumentMultimap argMultimap,
            Function<T, Quantity> getter) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            addPredicate(new ValueEqualsKeywordsPredicate<>(getter,
                    ParserUtil.parseQuantityKeywords(argMultimap.getValue(prefix).get())));
        }
    }

    /**
     * Adds a {@code ValueWithinRangePredicate} for quantity values to the list of predicates.
     * It adds the predicate as long as either the lower or upper bound of the range is provided,
     * and fills the unprovided bound with a default value.
     *
     * @param fromPrefix Prefix for the lower bound of the range.
     * @param toPrefix Prefix for the upper bound of the range.
     * @param argMultimap ArgumentMultimap to get the value associated with a prefix.
     * @param getter Function to get the quantity to be tested.
     * @throws ParseException if the given keywords is invalid.
     */
    public void addQuantityWithinRangePredicate(Prefix fromPrefix, Prefix toPrefix,
            ArgumentMultimap argMultimap, Function<T, Quantity> getter) throws ParseException {
        Optional<String> optionalMinQuantityKeyword = argMultimap.getValue(fromPrefix);
        Optional<String> optionalMaxQuantityKeyword = argMultimap.getValue(toPrefix);

        if (optionalMinQuantityKeyword.isEmpty() && optionalMaxQuantityKeyword.isEmpty()) {
            return;
        }

        String defaultKeywordMinQuantity = String.valueOf(Integer.MIN_VALUE);
        String defaultKeywordMaxQuantity = String.valueOf(Integer.MAX_VALUE);

        String minQuantityKeyword = optionalMinQuantityKeyword.orElse(defaultKeywordMinQuantity);
        String maxQuantityKeyword = optionalMaxQuantityKeyword.orElse(defaultKeywordMaxQuantity);

        addPredicate(new ValueWithinRangePredicate<>(
                getter,
                ParserUtil.parseInternalQuantity(minQuantityKeyword),
                ParserUtil.parseInternalQuantity(maxQuantityKeyword)));
    }

    /**
     * Adds a {@code StringContainsKeywordsPredicate} to the list of predicates.
     *
     * @param prefix Prefix of keyword.
     * @param argMultimap ArgumentMultimap to get the value associated with a prefix.
     * @param getter Function to get the string to be tested.
     * @throws ParseException if the given keywords is invalid.
     */
    public void addStringContainsKeywordsPredicate(Prefix prefix, ArgumentMultimap argMultimap,
            Function<T, ?> getter) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            addPredicate(new StringContainsKeywordsPredicate<>(getter,
                    ParserUtil.parseKeywords(argMultimap.getValue(prefix).get())));
        }
    }

    /**
     * Checks if there are no predicates to test against.
     *
     * @return True if there are no predicates to test against. False otherwise.
     */
    public boolean hasNoPredicates() {
        return predicates.isEmpty();
    }

    @Override
    public boolean test(T object) {
        return predicates.stream()
                .map(predicate -> predicate.test(object))
                .reduce(Boolean.TRUE, Boolean::logicalAnd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateCollection // instanceof handles nulls
                && hasSamePredicates(((PredicateCollection<T>) other).predicates)); // state check
    }
}
