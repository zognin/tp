package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.ParserFunction;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.Prefix;

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
     * Adds a {@code ValueInListPredicate} to the list of predicates.
     *
     * @param prefix Prefix of keyword.
     * @param argMultimap ArgumentMultimap to get the value associated with a prefix.
     * @param getter Function to get the quantity to be tested.
     * @param parser ParserFunction to parse the input to a list of values.
     * @param <S> Type of the values in the list.
     * @throws ParseException if the given input is invalid.
     */
    public <S> void addValueInListPredicate(Prefix prefix, ArgumentMultimap argMultimap,
            Function<T, S> getter, ParserFunction<List<S>> parser) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            addPredicate(new ValueInListPredicate<>(getter, parser.apply(argMultimap.getValue(prefix).get())));
        }
    }

    /**
     * Adds a {@code ValueWithinRangePredicate} to the list of predicates.
     * It adds the predicate as long as either the lower or upper bound of the range is provided,
     * and fills any unprovided bounds with a default value.
     *
     * @param fromPrefix Prefix for the lower bound of the range.
     * @param toPrefix Prefix for the upper bound of the range.
     * @param defaultMin Default lower bound of the range.
     * @param defaultMax Default upper bound of the range.
     * @param argMultimap ArgumentMultimap to get the value associated with a prefix.
     * @param parser ParserFunction to parse the inputs.
     * @param getter Function to get the value to be tested.
     * @param <S> Type of the values.
     * @throws ParseException if the given input is invalid.
     */
    public <S extends Comparable<S>> void addValueWithinRangePredicate(Prefix fromPrefix, Prefix toPrefix,
            String defaultMin, String defaultMax, ArgumentMultimap argMultimap, ParserFunction<S> parser,
            Function<T, S> getter) throws ParseException {
        Optional<String> optionalMinValue = argMultimap.getValue(fromPrefix);
        Optional<String> optionalMaxValue = argMultimap.getValue(toPrefix);

        if (optionalMinValue.isEmpty() && optionalMaxValue.isEmpty()) {
            return;
        }

        String minQuantity = optionalMinValue.orElse(defaultMin);
        String maxQuantity = optionalMaxValue.orElse(defaultMax);

        addPredicate(new ValueWithinRangePredicate<>(getter,
                parser.apply(minQuantity), parser.apply(maxQuantity)));
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
