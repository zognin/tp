package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericQuantityStub;

public class ValueInListPredicateTest {
    @Test
    public void equals() {
        List<Quantity> firstPredicateQuantityList = Collections.singletonList(new Quantity("5"));
        List<Quantity> secondPredicateQuantityList = Arrays.asList(new Quantity("5"), new Quantity("10"));
        ValueInListPredicate<GenericQuantityStub, Quantity> firstPredicate =
                new ValueInListPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        ValueInListPredicate<GenericQuantityStub, Quantity> secondPredicate =
                new ValueInListPredicate<>(GenericQuantityStub::getQuantity, secondPredicateQuantityList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ValueInListPredicate<GenericQuantityStub, Quantity> firstPredicateCopy =
                new ValueInListPredicate<>(GenericQuantityStub::getQuantity, firstPredicateQuantityList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different quantities -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_valueInList_returnsTrue() {
        ValueInListPredicate<GenericQuantityStub, Quantity> predicate =
                new ValueInListPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("5"));

        // One value, it is a match
        assertTrue(predicate.test(genericQuantityStub));

        // Multiple values, at least one match
        predicate = new ValueInListPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("10")));
        assertTrue(predicate.test(genericQuantityStub));
    }

    @Test
    public void test_valueNotInList_returnsFalse() {
        ValueInListPredicate<GenericQuantityStub, Quantity> predicate =
                new ValueInListPredicate<>(GenericQuantityStub::getQuantity, List.of(new Quantity("5")));
        GenericQuantityStub genericQuantityStub = new GenericQuantityStub(new Quantity("10"));

        // One value, no match
        assertFalse(predicate.test(genericQuantityStub));

        // Multiple values, no match
        predicate = new ValueInListPredicate<>(GenericQuantityStub::getQuantity,
                List.of(new Quantity("5"), new Quantity("15")));
        assertFalse(predicate.test(genericQuantityStub));
    }
}
