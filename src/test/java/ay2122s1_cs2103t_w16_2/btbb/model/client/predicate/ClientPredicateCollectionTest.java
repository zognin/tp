package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;

public class ClientPredicateCollectionTest {
    private void addPredicates(ClientPredicateCollection clientPredicateCollection,
                               List<Predicate<Client>> clientPredicates) {
        for (Predicate<Client> clientPredicate : clientPredicates) {
            clientPredicateCollection.addClientPredicate(clientPredicate);
        }
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(List.of("Alice", "Bob"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(List.of("9427", "3217"));
        AddressContainsKeywordsPredicate addressContainsKeywordsPredicate =
                new AddressContainsKeywordsPredicate(List.of("Yishun", "Geylang"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(List.of("alice@gmail.com", "bob@gmail.com"));
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate));
        ClientPredicateCollection diffOrderClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(diffOrderClientPredicateCollection, List.of(phoneContainsKeywordsPredicate,
                addressContainsKeywordsPredicate, nameContainsKeywordsPredicate, emailContainsKeywordsPredicate));

        // same order - must be equal
        assertEquals(clientPredicateCollection, clientPredicateCollection);
        // diff order - must be equal
        assertEquals(clientPredicateCollection, diffOrderClientPredicateCollection);
    }

    @Test
    public void notEquals() {
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(List.of("Alice", "Bob"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(List.of("9427", "3217"));
        AddressContainsKeywordsPredicate addressContainsKeywordsPredicate =
                new AddressContainsKeywordsPredicate(List.of("Yishun", "Geylang"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(List.of("alice@gmail.com", "bob@gmail.com"));
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate));
        ClientPredicateCollection incompleteClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(incompleteClientPredicateCollection, List.of(phoneContainsKeywordsPredicate,
                addressContainsKeywordsPredicate));
        ClientPredicateCollection emptyClientPredicateCollection = new ClientPredicateCollection();

        // Different No. of predicates - not equal
        assertNotEquals(clientPredicateCollection, incompleteClientPredicateCollection);
        // One has no predicates - not equal
        assertNotEquals(clientPredicateCollection, emptyClientPredicateCollection);
    }

    @Test
    public void test_comboTestResult() {
        Client client = new ClientBuilder().withName("Alice").withPhone("94273415")
                .withEmail("alice@gmail.com").withAddress("Main Street").build();
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate =
                new NameContainsKeywordsPredicate(List.of("Alice", "Bob"));
        PhoneContainsKeywordsPredicate phoneContainsKeywordsPredicate =
                new PhoneContainsKeywordsPredicate(List.of("9427", "3217"));
        AddressContainsKeywordsPredicate addressContainsKeywordsPredicate =
                new AddressContainsKeywordsPredicate(List.of("Yishun", "Geylang"));
        EmailContainsKeywordsPredicate emailContainsKeywordsPredicate =
                new EmailContainsKeywordsPredicate(List.of("alice@gmail.com", "bob@gmail.com"));
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate));
        boolean expectedResult = nameContainsKeywordsPredicate.test(client)
                && phoneContainsKeywordsPredicate.test(client) && addressContainsKeywordsPredicate.test(client)
                && emailContainsKeywordsPredicate.test(client);
        assertEquals(expectedResult, clientPredicateCollection.test(client));
    }
}
