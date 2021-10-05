package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.AddClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyUserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import javafx.collections.ObservableList;

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
        assertEquals(Arrays.asList(validClient), modelStub.clientsAdded);
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

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private class ModelStubWithClient extends ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return this.client.isSameClient(client);
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        @Override
        public boolean hasClient(Client client) {
            requireNonNull(client);
            return clientsAdded.stream().anyMatch(client::isSameClient);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            clientsAdded.add(client);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
