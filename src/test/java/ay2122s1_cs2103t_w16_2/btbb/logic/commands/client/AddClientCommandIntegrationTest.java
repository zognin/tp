package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_CLIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

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

        assertCommandSuccessWithTabChange(new AddClientCommand(validClientDescriptor), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getAddressBook().getClientList().get(0);
        ClientDescriptor clientInListDescriptor = new ClientDescriptorBuilder(clientInList).build();
        assertCommandFailure(new AddClientCommand(clientInListDescriptor), model, MESSAGE_DUPLICATE_CLIENT);
    }
}
