package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that contains a single order.
 */
public class ModelStubWithOrder extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final Order order;

    /**
     * Constructs a ModelStubWithOrder object which contains the given order.
     *
     * @param order The order that this model should contain.
     */
    public ModelStubWithOrder(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableList(clientsAdded);
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return this.order.isSameOrder(order);
    }
}
