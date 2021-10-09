package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_NAME_ALICE_BOB_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_PHONE_9427_3217_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.addPredicates;
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

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericStringPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

/**
 * Contains integration tests (interaction with the Model) for {@code FindClientCommand}.
 */
public class FindClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PredicateCollection<Client> predicateCollection = new PredicateCollection<>();
        addPredicates(predicateCollection, List.of(CLIENT_NAME_ALICE_BOB_PREDICATE,
                CLIENT_PHONE_9427_3217_PREDICATE,
                CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE, CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        PredicateCollection<Client> diffOrderPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderPredicateCollection, List.of(CLIENT_PHONE_9427_3217_PREDICATE,
                CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE, CLIENT_NAME_ALICE_BOB_PREDICATE,
                CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        PredicateCollection<Client> diffAmountPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderPredicateCollection, List.of(CLIENT_NAME_ALICE_BOB_PREDICATE,
                CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE));

        FindClientCommand findCommand = new FindClientCommand(predicateCollection);
        FindClientCommand diffOrderFindCommand = new FindClientCommand(diffOrderPredicateCollection);
        FindClientCommand diffAmountFindCommand = new FindClientCommand(diffAmountPredicateCollection);

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindClientCommand findCommandCopy = new FindClientCommand(predicateCollection);
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
        PredicateCollection<Client> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(
                prepareAndGetClientPredicate("Kurz Elle Kunz", Client::getName)
        );
        predicateCollection.addPredicate(
                prepareAndGetClientPredicate("9535 9482 2427", Client::getPhone)
        );
        predicateCollection.addPredicate(
                prepareAndGetClientPredicate("wall michegan tokyo", Client::getAddress)
        );
        predicateCollection.addPredicate(
                prepareAndGetClientPredicate("heinz werner lydia", Client::getEmail)
        );
        FindClientCommand command = new FindClientCommand(predicateCollection);
        expectedModel.updateFilteredClientList(predicateCollection);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Client>}.
     */
    private GenericStringPredicate<Client> prepareAndGetClientPredicate(String input, Function<Client, ?> getter) {
        List<String> keywords = List.of(input.split("\\s+"));
        return new GenericStringPredicate<>(getter, keywords);
    }
}
