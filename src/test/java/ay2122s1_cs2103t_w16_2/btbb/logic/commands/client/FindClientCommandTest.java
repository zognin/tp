package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollectionTest.ADDRESS_YISHUN_GEYLANG_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollectionTest.EMAIL_ALICE_BOB_GMAIL_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollectionTest.NAME_ALICE_BOB_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollectionTest.PHONE_9427_3217_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollectionTest.addPredicates;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.AddressContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.EmailContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.NameContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.PhoneContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindClientCommand}.
 */
public class FindClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        addPredicates(clientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                PHONE_9427_3217_PREDICATE, ADDRESS_YISHUN_GEYLANG_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        ClientPredicateCollection diffOrderClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(diffOrderClientPredicateCollection, List.of(PHONE_9427_3217_PREDICATE,
                ADDRESS_YISHUN_GEYLANG_PREDICATE, NAME_ALICE_BOB_PREDICATE, EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        ClientPredicateCollection diffAmountClientPredicateCollection = new ClientPredicateCollection();
        addPredicates(diffOrderClientPredicateCollection, List.of(NAME_ALICE_BOB_PREDICATE,
                EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        FindClientCommand findCommand = new FindClientCommand(clientPredicateCollection);
        FindClientCommand diffOrderFindCommand = new FindClientCommand(diffOrderClientPredicateCollection);
        FindClientCommand diffAmountFindCommand = new FindClientCommand(diffAmountClientPredicateCollection);

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindClientCommand findCommandCopy = new FindClientCommand(clientPredicateCollection);
        assertTrue(findCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(findCommand.equals(1));

        // null -> returns false
        assertFalse(findCommand.equals(null));

        // different order of client predicates -> returns true
        assertTrue(findCommand.equals(diffOrderFindCommand));

        // different amount of client predicates -> returns false
        assertFalse(findCommand.equals(diffAmountFindCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        clientPredicateCollection.addClientPredicate(
                prepareAndGetClientPredicate("Kurz Elle Kunz", NameContainsKeywordsPredicate::new)
        );
        clientPredicateCollection.addClientPredicate(
                prepareAndGetClientPredicate("9535 9482 2427", PhoneContainsKeywordsPredicate::new)
        );
        clientPredicateCollection.addClientPredicate(
                prepareAndGetClientPredicate("wall michegan tokyo", AddressContainsKeywordsPredicate::new)
        );
        clientPredicateCollection.addClientPredicate(
                prepareAndGetClientPredicate("heinz werner lydia", EmailContainsKeywordsPredicate::new)
        );
        FindClientCommand command = new FindClientCommand(clientPredicateCollection);
        expectedModel.updateFilteredClientList(clientPredicateCollection);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Client>}.
     */
    private Predicate<Client> prepareAndGetClientPredicate(String input,
                                                         Function<List<String>, Predicate<Client>> predicateFunction) {
        List<String> keywords = List.of(input.split("\\s+"));
        return predicateFunction.apply(keywords);
    }
}
