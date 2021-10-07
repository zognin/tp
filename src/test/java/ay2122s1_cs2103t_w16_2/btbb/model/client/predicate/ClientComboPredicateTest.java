package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;

public class ClientComboPredicateTest {
    private void addPredicates(ClientComboPredicate clientComboPredicate, Predicate<Client> ...clientPredicates) {
        for (Predicate<Client> clientPredicate : clientPredicates) {
            clientComboPredicate.addClientPredicate(clientPredicate);
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
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        addPredicates(clientComboPredicate, nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate);
        ClientComboPredicate diffOrderClientComboPredicate = new ClientComboPredicate();
        addPredicates(diffOrderClientComboPredicate, phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate,
                nameContainsKeywordsPredicate, emailContainsKeywordsPredicate);

        // same order - must be equal
        assertEquals(clientComboPredicate, clientComboPredicate);
        // diff order - must be equal
        assertEquals(clientComboPredicate, diffOrderClientComboPredicate);
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
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        addPredicates(clientComboPredicate, nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate);
        ClientComboPredicate incompleteClientComboPredicate = new ClientComboPredicate();
        addPredicates(incompleteClientComboPredicate, phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate);
        ClientComboPredicate emptyClientComboPredicate = new ClientComboPredicate();

        // Different No. of predicates - not equal
        assertNotEquals(clientComboPredicate, incompleteClientComboPredicate);
        // One has no predicates - not equal
        assertNotEquals(clientComboPredicate, emptyClientComboPredicate);
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
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        addPredicates(clientComboPredicate, nameContainsKeywordsPredicate,
                phoneContainsKeywordsPredicate, addressContainsKeywordsPredicate, emailContainsKeywordsPredicate);
        boolean expectedResult = nameContainsKeywordsPredicate.test(client)
                && phoneContainsKeywordsPredicate.test(client) && addressContainsKeywordsPredicate.test(client)
                && emailContainsKeywordsPredicate.test(client);
        assertEquals(expectedResult, clientComboPredicate.test(client));
    }
}
