package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * A Model stub that always accepts the client being added.
 */
public class ModelStubAcceptingClientAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();

    public ArrayList<Client> getClientsAdded() {
        return clientsAdded;
    }

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
