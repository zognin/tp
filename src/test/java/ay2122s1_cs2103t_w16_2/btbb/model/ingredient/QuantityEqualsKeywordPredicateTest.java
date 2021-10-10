package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericQuantityStub;

public class QuantityEqualsKeywordPredicateTest {
    @Test
    public void equals() {
        List<Quantity> firstPredicateQuantityList = Collections.singletonList(new Quantity("5"));
        List<Quantity> secondPredicateQuantityList = Arrays.asList(new Quantity("5"), new Quantity("10"));
        QuantityEqualsKeywordsPredicate<GenericQuantityStub> firstPredicate =
                new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        QuantityEqualsKeywordsPredicate<GenericQuantityStub> secondPredicate =
                new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, secondPredicateQuantityList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        QuantityEqualsKeywordsPredicate<GenericQuantityStub> firstPredicateCopy =
                new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different quantities -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_quantityEqualsKeywords_returnsTrue() {
        QuantityEqualsKeywordsPredicate<GenericQuantityStub> predicate =
                new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("5"));

        // One quantity, it is a match
        assertTrue(predicate.test(genericQuantityStub));

        // Multiple quantities, at least one match
        predicate = new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("10")));
        assertTrue(predicate.test(genericQuantityStub));
    }

    @Test
    public void test_quantityDoesNotEqualKeywords_returnsFalse() {
        QuantityEqualsKeywordsPredicate<GenericQuantityStub> predicate =
                new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("10"));

        // One quantity, no match
        assertFalse(predicate.test(genericQuantityStub));

        // Multiple quantities, no match
        predicate = new QuantityEqualsKeywordsPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("15")));
        assertFalse(predicate.test(genericQuantityStub));
    }
}
