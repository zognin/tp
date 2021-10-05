package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * A Model stub that always accepts the order being added.
 */
public class ModelStubAcceptingOrderAdded extends ModelStub {
    private final ArrayList<Order> ordersAdded = new ArrayList<>();

    public ArrayList<Order> getOrdersAdded() {
        return ordersAdded;
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
