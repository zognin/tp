package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;

public class ClientPredicateCollectionTest {
    public static final NameContainsKeywordsPredicate NAME_ALICE_BOB_PREDICATE =
            new NameContainsKeywordsPredicate(List.of("Alice", "Bob"));
    public static final PhoneContainsKeywordsPredicate PHONE_9427_3217_PREDICATE =
            new PhoneContainsKeywordsPredicate(List.of("9427", "3217"));
    public static final AddressContainsKeywordsPredicate ADDRESS_YISHUN_GEYLANG_PREDICATE =
            new AddressContainsKeywordsPredicate(List.of("Yishun", "Geylang"));
    public static final EmailContainsKeywordsPredicate EMAIL_ALICE_BOB_GMAIL_PREDICATE =
            new EmailContainsKeywordsPredicate(List.of("alice@gmail.com", "bob@gmail.com"));

    public static void addPredicates(ClientPredicateCollection clientPredicateCollection,
                               List<Predicate<Client>> clientPredicates) {
        for (Predicate<Client> clientPredicate : clientPredicates) {
            clientPredicateCollection.addClientPredicate(clientPredicate);
        }
    }

    @Test
    public void equals() {
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                PHONE_9427_3217_PREDICATE, ADDRESS_YISHUN_GEYLANG_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        ClientPredicateCollection sameOrderClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(sameOrderClientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                PHONE_9427_3217_PREDICATE, ADDRESS_YISHUN_GEYLANG_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        ClientPredicateCollection diffOrderClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(diffOrderClientPredicateCollection, List.of(PHONE_9427_3217_PREDICATE,
                ADDRESS_YISHUN_GEYLANG_PREDICATE, NAME_ALICE_BOB_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        ClientPredicateCollection incompleteClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(incompleteClientPredicateCollection, List.of(PHONE_9427_3217_PREDICATE,
                ADDRESS_YISHUN_GEYLANG_PREDICATE));

        ClientPredicateCollection emptyClientPredicateCollection = new ClientPredicateCollection();

        // different types -> returns false
        assertFalse(clientPredicateCollection.equals(1));

        // null -> returns false
        assertFalse(clientPredicateCollection.equals(null));

        // same object - must be equal
        assertEquals(clientPredicateCollection, clientPredicateCollection);

        // same order - must be equal
        assertEquals(clientPredicateCollection, sameOrderClientPredicateCollection);

        // diff order - must be equal
        assertEquals(clientPredicateCollection, diffOrderClientPredicateCollection);

        // Different No. of predicates - not equal
        assertNotEquals(clientPredicateCollection, incompleteClientPredicateCollection);

        // One has no predicates - not equal
        assertNotEquals(clientPredicateCollection, emptyClientPredicateCollection);
    }

    @Test
    public void test() {
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                PHONE_9427_3217_PREDICATE, ADDRESS_YISHUN_GEYLANG_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        // Client with everything matching
        assertTrue(clientPredicateCollection.test(new ClientBuilder().withName("Alice").withPhone("94273217")
                .withEmail("alice@gmail.com").withAddress("Yishun Ave 2").build()));

        // Client with matching email, phone, address but not name
        assertFalse(clientPredicateCollection.test(new ClientBuilder().withName("Ben").withPhone("94273217")
                .withEmail("alice@gmail.com").withAddress("Yishun Ave 2").build()));

        // Client with matching name, phone, address but not email
        assertFalse(clientPredicateCollection.test(new ClientBuilder().withName("Alice").withPhone("94273217")
                .withEmail("alexia@gmail.com").withAddress("Yishun Ave 2").build()));

        // Client with matching email, name, address but not phone
        assertFalse(clientPredicateCollection.test(new ClientBuilder().withName("Alice").withPhone("95774321")
                .withEmail("alice@gmail.com").withAddress("Yishun Ave 2").build()));

        // Client with matching email, phone, name but not address
        assertFalse(clientPredicateCollection.test(new ClientBuilder().withName("Alice").withPhone("94273217")
                .withEmail("alice@gmail.com").withAddress("Bukit Batok Ave 2").build()));
    }
}
