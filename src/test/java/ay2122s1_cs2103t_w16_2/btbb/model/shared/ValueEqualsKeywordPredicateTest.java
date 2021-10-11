package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericQuantityStub;

public class ValueEqualsKeywordPredicateTest {
    @Test
    public void equals() {
        List<Quantity> firstPredicateQuantityList = Collections.singletonList(new Quantity("5"));
        List<Quantity> secondPredicateQuantityList = Arrays.asList(new Quantity("5"), new Quantity("10"));
        ValueEqualsKeywordsPredicate<GenericQuantityStub, Quantity> firstPredicate =
                new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        ValueEqualsKeywordsPredicate<GenericQuantityStub, Quantity> secondPredicate =
                new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, secondPredicateQuantityList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ValueEqualsKeywordsPredicate<GenericQuantityStub, Quantity> firstPredicateCopy =
                new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different quantities -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_valueEqualsKeywords_returnsTrue() {
        ValueEqualsKeywordsPredicate<GenericQuantityStub, Quantity> predicate =
                new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("5"));

        // One quantity, it is a match
        assertTrue(predicate.test(genericQuantityStub));

        // Multiple quantities, at least one match
        predicate = new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("10")));
        assertTrue(predicate.test(genericQuantityStub));
    }

    @Test
    public void test_valueDoesNotEqualKeywords_returnsFalse() {
        ValueEqualsKeywordsPredicate<GenericQuantityStub, Quantity> predicate =
                new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("10"));

        // One quantity, no match
        assertFalse(predicate.test(genericQuantityStub));

        // Multiple quantities, no match
        predicate = new ValueEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("15")));
        assertFalse(predicate.test(genericQuantityStub));
    }
}
