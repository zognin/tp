package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        ClientPredicateCollection diffOrderClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(diffOrderClientPredicateCollection, List.of(PHONE_9427_3217_PREDICATE,
                ADDRESS_YISHUN_GEYLANG_PREDICATE, NAME_ALICE_BOB_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));
        ClientPredicateCollection incompleteClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(incompleteClientPredicateCollection, List.of(PHONE_9427_3217_PREDICATE,
                ADDRESS_YISHUN_GEYLANG_PREDICATE));
        ClientPredicateCollection emptyClientPredicateCollection = new ClientPredicateCollection();

        // same order - must be equal
        assertEquals(clientPredicateCollection, clientPredicateCollection);
        // diff order - must be equal
        assertEquals(clientPredicateCollection, diffOrderClientPredicateCollection);
        // Different No. of predicates - not equal
        assertNotEquals(clientPredicateCollection, incompleteClientPredicateCollection);
        // One has no predicates - not equal
        assertNotEquals(clientPredicateCollection, emptyClientPredicateCollection);
    }

    @Test
    public void test_comboTestResult() {
        Client client = new ClientBuilder().withName("Alice").withPhone("94273415")
                .withEmail("alice@gmail.com").withAddress("Main Street").build();
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                PHONE_9427_3217_PREDICATE, ADDRESS_YISHUN_GEYLANG_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));
        boolean expectedResult = NAME_ALICE_BOB_PREDICATE.test(client)
                && PHONE_9427_3217_PREDICATE.test(client) && ADDRESS_YISHUN_GEYLANG_PREDICATE.test(client)
                && EMAIL_ALICE_BOB_GMAIL_PREDICATE.test(client);
        assertEquals(expectedResult, clientPredicateCollection.test(client));
    }
}
