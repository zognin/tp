package seedu.btbb.logic.commands;

import static seedu.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.btbb.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.btbb.logic.commands.client.AddClientCommand;
import seedu.btbb.logic.descriptors.ClientDescriptor;
import seedu.btbb.model.Model;
import seedu.btbb.model.ModelManager;
import seedu.btbb.model.UserPrefs;
import seedu.btbb.model.client.Client;
import seedu.btbb.testutil.ClientBuilder;
import seedu.btbb.testutil.ClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddClientCommand}.
 */
public class AddClientCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addClient(validClient);
        ClientDescriptor validClientDescriptor = new ClientDescriptorBuilder(validClient).build();

        assertCommandSuccess(new AddClientCommand(validClientDescriptor), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getAddressBook().getClientList().get(0);
        ClientDescriptor clientInListDescriptor = new ClientDescriptorBuilder(clientInList).build();
        assertCommandFailure(new AddClientCommand(clientInListDescriptor), model,
                AddClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }
}
