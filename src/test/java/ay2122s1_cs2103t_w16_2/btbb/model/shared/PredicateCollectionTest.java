package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.GenericDummy;

public class PredicateCollectionTest {
    // Client predicates
    public static final StringContainsKeywordPredicate<Client> CLIENT_NAME_ALICE_BOB_PREDICATE =
            new StringContainsKeywordPredicate<>(Client::getName, List.of("Alice", "Bob"));
    public static final StringContainsKeywordPredicate<Client> CLIENT_PHONE_9427_3217_PREDICATE =
            new StringContainsKeywordPredicate<>(Client::getPhone, List.of("9427", "3217"));
    public static final StringContainsKeywordPredicate<Client> CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new StringContainsKeywordPredicate<>(Client::getAddress, List.of("Yishun", "Geylang"));
    public static final StringContainsKeywordPredicate<Client> CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE =
            new StringContainsKeywordPredicate<>(Client::getEmail, List.of("alice@gmail.com", "bob@gmail.com"));

    // Order predicates
    public static final StringContainsKeywordPredicate<Order> CLIENT_NAME_CAROL_DAVID_PREDICATE =
            new StringContainsKeywordPredicate<>(Order::getClientName, List.of("Carol", "David"));
    public static final StringContainsKeywordPredicate<Order> CLIENT_PHONE_9110_3216_PREDICATE =
            new StringContainsKeywordPredicate<>(Order::getClientPhone, List.of("9110", "3216"));
    public static final StringContainsKeywordPredicate<Order> CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE =
            new StringContainsKeywordPredicate<>(Order::getClientAddress, List.of("Eunos", "Bishan"));

    // Dummy predicates
    public static final StringContainsKeywordPredicate<GenericDummy> GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE =
            new StringContainsKeywordPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
    public static final StringContainsKeywordPredicate<GenericDummy> GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new StringContainsKeywordPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Geylang"));

    public static <T> void addPredicates(PredicateCollection<T> predicateCollection,
                                         List<Predicate<T>> predicates) {
        for (Predicate<T> predicate : predicates) {
            predicateCollection.addPredicate(predicate);
        }
    }

    @Test
    public void equals() {
        PredicateCollection<GenericDummy> genericDummyPredicateCollection = new PredicateCollection<>();
        addPredicates(genericDummyPredicateCollection, List.of(GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE,
                GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericDummy> sameOrderGenericDummyPredicateCollection = new PredicateCollection<>();
        addPredicates(sameOrderGenericDummyPredicateCollection, List.of(GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE,
                GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericDummy> diffOrderGenericDummyPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderGenericDummyPredicateCollection, List.of(GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE,
                GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE));

        PredicateCollection<GenericDummy> incompleteGenericDummyPredicateCollection = new PredicateCollection<>();
        addPredicates(incompleteGenericDummyPredicateCollection,
                List.of(GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericDummy> emptyGenericDummyPredicateCollection = new PredicateCollection<>();

        // different types -> returns false
        assertFalse(genericDummyPredicateCollection.equals(1));

        // null -> returns false
        assertFalse(genericDummyPredicateCollection.equals(null));

        // same object - must be equal
        assertEquals(genericDummyPredicateCollection, genericDummyPredicateCollection);

        // same order - must be equal
        assertEquals(genericDummyPredicateCollection, sameOrderGenericDummyPredicateCollection);

        // diff order - must be equal
        assertEquals(genericDummyPredicateCollection, diffOrderGenericDummyPredicateCollection);

        // Different No. of predicates - not equal
        assertNotEquals(genericDummyPredicateCollection, incompleteGenericDummyPredicateCollection);

        // One has no predicates - not equal
        assertNotEquals(genericDummyPredicateCollection, emptyGenericDummyPredicateCollection);
    }

    @Test
    public void test() {
        PredicateCollection<GenericDummy> genericDummyPredicateCollection = new PredicateCollection<>();
        addPredicates(genericDummyPredicateCollection, List.of(GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE,
                GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        // GenericDummy with everything matching
        assertTrue(genericDummyPredicateCollection.test(new GenericDummy(new GenericString("Alice"),
                new GenericString("Yishun"))));

        // GenericDummy with matching name but not address
        assertFalse(genericDummyPredicateCollection.test(new GenericDummy(new GenericString("Alice"),
                new GenericString("Serangoon"))));

        // GenericDummy with matching address but not name
        assertFalse(genericDummyPredicateCollection.test(new GenericDummy(new GenericString("Carol"),
                new GenericString("Yishun"))));
    }
}
