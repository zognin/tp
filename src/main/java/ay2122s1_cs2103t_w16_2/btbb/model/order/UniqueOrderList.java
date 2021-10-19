package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

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
     * Returns true if the list contains an equivalent order as the given argument.
     */
    public boolean contains(Order toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameOrder);
    }

    /**
     * Removes the equivalent order from the list of orders.
     * The order must exist in the list.
     *
     * @param toRemove The order to remove from the list.
     * @throws NotFoundException when there is no equivalent order found in the list.
     */
    public void remove(Order toRemove) throws NotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException(Order.class.getName());
        }
    }

    /**
     * Replaces the existing target Order in the list with an edited Order.
     *
     * @param target The target order to replace.
     * @param editedOrder The edited order to replace with.
     * @throws NotFoundException If the target order does not exist in the list.
     */
    public void setOrder(Order target, Order editedOrder) throws NotFoundException {
        requireAllNonNull(target, editedOrder);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException(Order.class.getName());
        }

        internalList.set(index, editedOrder);
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
     * Returns the top ten clients with the most orders.
     *
     * @return Top ten clients with the most orders.
     */
    public List<Map.Entry<Pair<GenericString, Phone>, Integer>> getTopTenClients() {
        HashMap<Pair<GenericString, Phone>, Integer> clientOrderCountKeyValuePair =
                getClientOrderCountKeyValuePair();

        List<Map.Entry<Pair<GenericString, Phone>, Integer>> topTenClients =
                getTopTenClientsFromHashMap(clientOrderCountKeyValuePair);

        return topTenClients;
    }

    private HashMap<Pair<GenericString, Phone>, Integer> getClientOrderCountKeyValuePair() {
        HashMap<Pair<GenericString, Phone>, Integer> clientOrderCountKeyValuePair = new HashMap<>();

        internalList.stream().forEach(order -> {
            GenericString clientName = order.getClientName();
            Phone clientPhone = order.getClientPhone();
            Pair<GenericString, Phone> clientNameAndPhone = new Pair<>(clientName, clientPhone);
            if (clientOrderCountKeyValuePair.containsKey(clientNameAndPhone)) {
                int orderCount = clientOrderCountKeyValuePair.get(clientNameAndPhone);
                clientOrderCountKeyValuePair.put(clientNameAndPhone, ++orderCount);
            } else {
                clientOrderCountKeyValuePair.put(clientNameAndPhone, 1);
            }
        });

        return clientOrderCountKeyValuePair;
    }

    private List<Map.Entry<Pair<GenericString, Phone>, Integer>> getTopTenClientsFromHashMap(
            HashMap<Pair<GenericString, Phone>, Integer> hashMap) {
        List<Map.Entry<Pair<GenericString, Phone>, Integer>> clientOrderCountPairs =
                hashMap.entrySet()
                        .stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toList());

        return clientOrderCountPairs.size() <= 10 ? clientOrderCountPairs : clientOrderCountPairs.subList(0, 10);
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
