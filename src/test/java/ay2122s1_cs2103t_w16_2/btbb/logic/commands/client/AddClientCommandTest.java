package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStub;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingClientAdded;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubWithClient;

public class AddClientCommandTest {
    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();
        ClientDescriptor validClientDescriptor = new ClientDescriptorBuilder(validClient).build();

        CommandResult commandResult = new AddClientCommand(validClientDescriptor).execute(modelStub);

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.getClientsAdded());
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client validClient = new ClientBuilder().build();
        ClientDescriptor validClientDescriptor = new ClientDescriptorBuilder(validClient).build();
        AddClientCommand addClientCommand = new AddClientCommand(validClientDescriptor);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class,
            AddClientCommand.MESSAGE_DUPLICATE_CLIENT, () -> addClientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ClientDescriptor aliceDescriptor = new ClientDescriptorBuilder().withName("Alice").build();
        ClientDescriptor bobDescriptor = new ClientDescriptorBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(aliceDescriptor);
        AddClientCommand addBobCommand = new AddClientCommand(bobDescriptor);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(aliceDescriptor);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
}
