package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UniqueOrderList implements Iterable<Order> {
    private final ObservableList<Order> internalList = FXCollections.observableArrayList();
    private final ObservableList<Order> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds an order to the list.
     *
     * @param toAdd Order to be added to the list.
     */
    public void add(Order toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the contents of this list with {@code orders}.
     *
     * @param orders Orders of the list.
     */
    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        internalList.setAll(orders);
    }

    /**
     * Returns the backing list as an unmodifiable Observable List.
     *
     * @return The full backing order list.
     */
    public ObservableList<Order> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Order> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueOrderList // instanceof handles nulls
                && internalList.equals(((UniqueOrderList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
