package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.YearMonth;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
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
        sortOrders();
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
        sortOrders();
    }

    /**
     * Replaces the contents of this list with {@code orders}.
     *
     * @param orders Orders of the list.
     */
    public void setOrders(List<Order> orders) {
        requireAllNonNull(orders);
        internalList.setAll(orders);
        sortOrders();
    }

    /**
     * Returns the revenue for the past twelve months.
     *
     * @return Revenue for the past twelve months.
     */
    public List<Entry<YearMonth, Double>> getRevenueForPastTwelveMonths() {
        Map<YearMonth, Double> orderToMonthlyRevenueMap = new HashMap<>(12);
        fillInitialValuesForRevenueMap(orderToMonthlyRevenueMap);
        fillRevenueForPastTwelveMonths(orderToMonthlyRevenueMap);

        return getRevenueForYearFromMap(orderToMonthlyRevenueMap);
    }

    private void fillInitialValuesForRevenueMap(Map<YearMonth, Double> orderToMonthlyRevenueMap) {
        YearMonth currentYearMonth = YearMonth.now();
        for (int i = 0; i < 12; i++) {
            orderToMonthlyRevenueMap.put(currentYearMonth, 0.0);
            currentYearMonth = currentYearMonth.minusMonths(1);
        }
    }

    private void fillRevenueForPastTwelveMonths(Map<YearMonth, Double> orderToMonthlyRevenueMap) {
        internalList.stream()
                .filter(order -> order.getCompletionStatus().getIsFinished())
                .forEach(order -> {
                    YearMonth currentYearMonthForOrder = order.getYearMonth();

                    if (orderToMonthlyRevenueMap.containsKey(currentYearMonthForOrder)) {
                        orderToMonthlyRevenueMap.put(
                                currentYearMonthForOrder,
                                orderToMonthlyRevenueMap.get(currentYearMonthForOrder)
                                        + order.getOrderPrice().doubleValue()
                        );
                    }
                });
    }

    private List<Entry<YearMonth, Double>> getRevenueForYearFromMap(Map<YearMonth, Double> orderToMonthlyRevenueMap) {
        return orderToMonthlyRevenueMap.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .collect(Collectors.toList());
    }

    /**
     * Returns the top 10 clients with the most orders.
     * Ties are broken arbitrarily eg. if there are multiple clients with the same number of orders, random
     * clients will be chosen.
     *
     * @return Top 10 clients with the most orders.
     */
    public List<Entry<OrderClient, Long>> getTopTenOrderClients() {
        Map<OrderClient, Long> clientToOrderCountMap = getClientToOrderCountMap();
        return getTopTenOrderClientsFromMap(clientToOrderCountMap);
    }

    private Map<OrderClient, Long> getClientToOrderCountMap() {
        return internalList.stream()
                .collect(Collectors.groupingBy(Order::getOrderClient, Collectors.counting()));
    }

    private List<Entry<OrderClient, Long>> getTopTenOrderClientsFromMap(
            Map<OrderClient, Long> hashMap) {
        return hashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * Returns the top 10 recipes.
     * Ties are broken arbitrarily eg. if there are multiple recipes with the same quantity, random
     * recipes will be chosen.
     *
     * @return Top 10 recipes.
     */
    public List<Entry<GenericString, Long>> getTopTenOrderRecipes() {
        Map<GenericString, Long> recipeToOrderCountMap = getRecipeToOrderCountMap();
        return getTopTenOrderRecipesFromMap(recipeToOrderCountMap);
    }

    private Map<GenericString, Long> getRecipeToOrderCountMap() {
        return internalList.stream()
                .collect(Collectors.groupingBy(Order::getRecipeName,
                        Collectors.<Order>summingLong(Order::getQuantityAsInt)));
    }

    private List<Entry<GenericString, Long>> getTopTenOrderRecipesFromMap(
            Map<GenericString, Long> hashMap) {
        return hashMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Entry.comparingByValue()))
                .limit(10)
                .collect(Collectors.toList());
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

    private void sortOrders() {
        internalList.sort(Order::compareTo);
    }
}
