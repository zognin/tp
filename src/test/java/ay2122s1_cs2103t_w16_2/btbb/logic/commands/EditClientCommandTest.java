package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showClientAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.EditClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Client editedClient = new ClientBuilder().build();
        ClientDescriptor descriptor = new ClientDescriptorBuilder(editedClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        ClientDescriptor descriptor = new ClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT, new ClientDescriptor());
        Client editedClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws NotFoundException {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
                new ClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ClientDescriptor descriptor = new ClientDescriptorBuilder(firstClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
                new ClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        ClientDescriptor descriptor = new ClientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getClientList().size());

        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex,
                new ClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST_CLIENT, DESC_AMY);

        // same values -> returns true
        ClientDescriptor copyDescriptor = new ClientDescriptor(DESC_AMY);
        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST_CLIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_SECOND_CLIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
    }
}
