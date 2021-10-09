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
    public static final GenericStringPredicate<Client> CLIENT_NAME_ALICE_BOB_PREDICATE =
            new GenericStringPredicate<>(Client::getName, List.of("Alice", "Bob"));
    public static final GenericStringPredicate<Client> CLIENT_PHONE_9427_3217_PREDICATE =
            new GenericStringPredicate<>(Client::getPhone, List.of("9427", "3217"));
    public static final GenericStringPredicate<Client> CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new GenericStringPredicate<>(Client::getAddress, List.of("Yishun", "Geylang"));
    public static final GenericStringPredicate<Client> CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE =
            new GenericStringPredicate<>(Client::getEmail, List.of("alice@gmail.com", "bob@gmail.com"));

    // Order predicates
    public static final GenericStringPredicate<Order> CLIENT_NAME_CAROL_DAVID_PREDICATE =
            new GenericStringPredicate<>(Order::getClientName, List.of("Carol", "David"));
    public static final GenericStringPredicate<Order> CLIENT_PHONE_9110_3216_PREDICATE =
            new GenericStringPredicate<>(Order::getClientPhone, List.of("9110", "3216"));
    public static final GenericStringPredicate<Order> CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE =
            new GenericStringPredicate<>(Order::getClientAddress, List.of("Eunos", "Bishan"));

    // Dummy predicates
    public static final GenericStringPredicate<GenericDummy> GENERIC_DUMMY_NAME_ALICE_BOB_PREDICATE =
            new GenericStringPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
    public static final GenericStringPredicate<GenericDummy> GENERIC_DUMMY_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new GenericStringPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Geylang"));

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
