package ay2122s1_cs2103t_w16_2.btbb.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericStub;

public class PredicateCollectionTest {
    // Client predicates
    public static final StringContainsKeywordsPredicate<Client> CLIENT_NAME_ALICE_BOB_PREDICATE =
            new StringContainsKeywordsPredicate<>(Client::getName, List.of("Alice", "Bob"));
    public static final StringContainsKeywordsPredicate<Client> CLIENT_PHONE_9427_3217_PREDICATE =
            new StringContainsKeywordsPredicate<>(Client::getPhone, List.of("9427", "3217"));
    public static final StringContainsKeywordsPredicate<Client> CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new StringContainsKeywordsPredicate<>(Client::getAddress, List.of("Yishun", "Geylang"));
    public static final StringContainsKeywordsPredicate<Client> CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE =
            new StringContainsKeywordsPredicate<>(Client::getEmail, List.of("alice@gmail.com", "bob@gmail.com"));

    // Ingredient predicates
    public static final StringContainsKeywordsPredicate<Ingredient> INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE =
            new StringContainsKeywordsPredicate<>(Ingredient::getName, List.of("Avocado", "Butter"));
    public static final ValueInListPredicate<Ingredient, Quantity> INGREDIENT_QUANTITY_5_550_PREDICATE =
            new ValueInListPredicate<>(Ingredient::getQuantity,
                    List.of(new Quantity("5"), new Quantity("550")));
    public static final ValueWithinRangePredicate<Ingredient, Quantity> INGREDIENT_QUANTITY_5_TO_600_PREDICATE =
            new ValueWithinRangePredicate<>(Ingredient::getQuantity, new Quantity("5"), new Quantity("600"));
    public static final StringContainsKeywordsPredicate<Ingredient> INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE =
            new StringContainsKeywordsPredicate<>(Ingredient::getUnit, List.of("whole", "grams"));

    // Order predicates
    public static final StringContainsKeywordsPredicate<Order> CLIENT_NAME_CAROL_DAVID_PREDICATE =
            new StringContainsKeywordsPredicate<>(Order::getClientName, List.of("Carol", "David"));
    public static final StringContainsKeywordsPredicate<Order> CLIENT_PHONE_9110_3216_PREDICATE =
            new StringContainsKeywordsPredicate<>(Order::getClientPhone, List.of("9110", "3216"));
    public static final StringContainsKeywordsPredicate<Order> CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE =
            new StringContainsKeywordsPredicate<>(Order::getClientAddress, List.of("Eunos", "Bishan"));

    // Generic stub predicates
    public static final StringContainsKeywordsPredicate<GenericStub> GENERIC_STUB_NAME_ALICE_BOB_PREDICATE =
            new StringContainsKeywordsPredicate<>(GenericStub::getName, List.of("Alice", "Bob"));
    public static final StringContainsKeywordsPredicate<GenericStub> GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new StringContainsKeywordsPredicate<>(GenericStub::getAddress, List.of("Yishun", "Geylang"));

    public static <T> void addPredicates(PredicateCollection<T> predicateCollection,
                                         List<Predicate<T>> predicates) {
        for (Predicate<T> predicate : predicates) {
            predicateCollection.addPredicate(predicate);
        }
    }

    @Test
    public void equals() {
        PredicateCollection<GenericStub> predicateCollection = new PredicateCollection<>();
        addPredicates(predicateCollection, List.of(GENERIC_STUB_NAME_ALICE_BOB_PREDICATE,
                GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericStub> sameOrderPredicateCollection = new PredicateCollection<>();
        addPredicates(sameOrderPredicateCollection, List.of(GENERIC_STUB_NAME_ALICE_BOB_PREDICATE,
                GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericStub> diffOrderPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderPredicateCollection, List.of(GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE,
                GENERIC_STUB_NAME_ALICE_BOB_PREDICATE));

        PredicateCollection<GenericStub> incompletePredicateCollection = new PredicateCollection<>();
        addPredicates(incompletePredicateCollection,
                List.of(GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        PredicateCollection<GenericStub> emptyGenericStubPredicateCollection = new PredicateCollection<>();

        // different types -> returns false
        assertFalse(predicateCollection.equals(1));

        // null -> returns false
        assertFalse(predicateCollection.equals(null));

        // same object - must be equal
        assertEquals(predicateCollection, predicateCollection);

        // same order - must be equal
        assertEquals(predicateCollection, sameOrderPredicateCollection);

        // diff order - must be equal
        assertEquals(predicateCollection, diffOrderPredicateCollection);

        // Different No. of predicates - not equal
        assertNotEquals(predicateCollection, incompletePredicateCollection);

        // One has no predicates - not equal
        assertNotEquals(predicateCollection, emptyGenericStubPredicateCollection);
    }

    @Test
    public void test() {
        PredicateCollection<GenericStub> genericStubPredicateCollection = new PredicateCollection<>();
        addPredicates(genericStubPredicateCollection, List.of(GENERIC_STUB_NAME_ALICE_BOB_PREDICATE,
                GENERIC_STUB_ADDRESS_YISHUN_GEYLANG_PREDICATE));

        // GenericStub with everything matching
        assertTrue(genericStubPredicateCollection.test(new GenericStub(new GenericString("Alice"),
                new GenericString("Yishun"))));

        // GenericStub with matching name but not address
        assertFalse(genericStubPredicateCollection.test(new GenericStub(new GenericString("Alice"),
                new GenericString("Serangoon"))));

        // GenericStub with matching address but not name
        assertFalse(genericStubPredicateCollection.test(new GenericStub(new GenericString("Carol"),
                new GenericString("Yishun"))));
    }
}
