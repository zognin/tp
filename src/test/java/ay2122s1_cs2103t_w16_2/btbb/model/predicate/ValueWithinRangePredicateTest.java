package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericQuantityStub;

public class ValueWithinRangePredicateTest {
    @Test
    public void equals() {
        Quantity firstPredicateMinQuantity = new Quantity("1");
        Quantity firstPredicateMaxQuantity = new Quantity("10");
        Quantity secondPredicateMinQuantity = new Quantity("3");
        Quantity secondPredicateMaxQuantity = new Quantity("7");

        ValueWithinRangePredicate<GenericQuantityStub, Quantity> firstPredicate =
                new ValueWithinRangePredicate<>(GenericQuantityStub::getQuantity,
                firstPredicateMinQuantity, firstPredicateMaxQuantity);
        ValueWithinRangePredicate<GenericQuantityStub, Quantity> secondPredicate =
                new ValueWithinRangePredicate<>(GenericQuantityStub::getQuantity,
                secondPredicateMinQuantity, secondPredicateMaxQuantity);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ValueWithinRangePredicate<GenericQuantityStub, Quantity> firstPredicateCopy =
                new ValueWithinRangePredicate<>(GenericQuantityStub::getQuantity,
                        firstPredicateMinQuantity, firstPredicateMaxQuantity);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different quantities -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_valueWithinRange_returnsTrue() {
        Quantity minQuantity = new Quantity("3");
        Quantity maxQuantity = new Quantity("7");
        ValueWithinRangePredicate<GenericQuantityStub, Quantity> predicate =
                new ValueWithinRangePredicate<>(GenericQuantityStub::getQuantity, minQuantity, maxQuantity);
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("5"));
        assertTrue(predicate.test(genericQuantityStub));

        // Start inclusive
        genericQuantityStub = new GenericQuantityStub(minQuantity);
        assertTrue(predicate.test(genericQuantityStub));

        // End inclusive
        genericQuantityStub = new GenericQuantityStub(maxQuantity);
        assertTrue(predicate.test(genericQuantityStub));
    }

    @Test
    public void test_valueOutOfRange_returnsFalse() {
        Quantity minQuantity = new Quantity("3");
        Quantity maxQuantity = new Quantity("7");
        ValueWithinRangePredicate<GenericQuantityStub, Quantity> predicate =
                new ValueWithinRangePredicate<>(GenericQuantityStub::getQuantity, minQuantity, maxQuantity);

        // Less than min
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("1"));
        assertFalse(predicate.test(genericQuantityStub));

        // Greater than max
        genericQuantityStub = new GenericQuantityStub(new Quantity("11"));
        assertFalse(predicate.test(genericQuantityStub));
    }
}
