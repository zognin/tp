package ay2122s1_cs2103t_w16_2.btbb.model;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {
    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     *
     * @return Observable view of clients list.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the ingredients list.
     * This list will not contain any duplicate ingredients.
     *
     * @return Observable view of ingredients list.
     */
    ObservableList<Ingredient> getIngredientList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     *
     * @return Observable view of orders list.
     */
    ObservableList<Order> getOrderList();
}
