package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that always accepts the order being added.
 */
public class ModelStubAcceptingOrderAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Order> ordersAdded = new ArrayList<>();

    public ArrayList<Order> getOrdersAdded() {
        return ordersAdded;
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableList(clientsAdded);
    }

    @Override
    public void addClient(Client client) {
        requireNonNull(client);
        clientsAdded.add(client);
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return ordersAdded.stream().anyMatch(order::isSameOrder);
    }

    @Override
    public void addOrder(Order order) {
        requireNonNull(order);
        ordersAdded.add(order);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }


}
