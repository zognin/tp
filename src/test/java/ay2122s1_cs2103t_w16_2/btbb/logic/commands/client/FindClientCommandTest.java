package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.AddressContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientComboPredicate;
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
        ClientComboPredicate firstPredicate = new ClientComboPredicate();
        firstPredicate.addClientPredicate(new NameContainsKeywordsPredicate(Collections.singletonList("first")));
        ClientComboPredicate secondPredicate = new ClientComboPredicate();
        secondPredicate.addClientPredicate(new NameContainsKeywordsPredicate(Collections.singletonList("second")));

        FindClientCommand findFirstCommand = new FindClientCommand(firstPredicate);
        FindClientCommand findSecondCommand = new FindClientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindClientCommand findFirstCommandCopy = new FindClientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noClientFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 0);
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate(" ", NameContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate(" ", AddressContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate(" ", EmailContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate(" ", PhoneContainsKeywordsPredicate::new)
        );
        FindClientCommand command = new FindClientCommand(clientComboPredicate);
        expectedModel.updateFilteredClientList(clientComboPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredClientList());
    }

    @Test
    public void execute_multipleKeywords_multipleClientsFound() {
        String expectedMessage = String.format(MESSAGE_CLIENTS_LISTED_OVERVIEW, 3);
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate("Kurz Elle Kunz", NameContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate("9535 9482 2427", PhoneContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate("wall michegan tokyo", AddressContainsKeywordsPredicate::new)
        );
        clientComboPredicate.addClientPredicate(
                prepareClientPredicate("heinz werner lydia", EmailContainsKeywordsPredicate::new)
        );
        FindClientCommand command = new FindClientCommand(clientComboPredicate);
        expectedModel.updateFilteredClientList(clientComboPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredClientList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate<Client>}.
     */
    private Predicate<Client> prepareClientPredicate(String input,
                                                     Function<List<String>, Predicate<Client>> predicateFunction) {
        List<String> keywords = List.of(input.trim().split("\\s+"));
        return predicateFunction.apply(keywords);
    }
}
