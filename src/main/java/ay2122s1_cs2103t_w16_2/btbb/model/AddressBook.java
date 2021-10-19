package ay2122s1_cs2103t_w16_2.btbb.model;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Map.Entry;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.UniqueClientList;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.UniqueIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.UniqueOrderList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient and .isSameIngredient comparisons)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueClientList clients;
    private final UniqueIngredientList ingredients;
    private final UniqueOrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniqueClientList();
        ingredients = new UniqueIngredientList();
        orders = new UniqueOrderList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Clients, Orders and Ingredients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setClients(clients);
    }

    /**
     * Replaces the contents of the ingredients list with {@code ingredients}
     * {@code ingredients} must not contain duplicate orders.
     *
     * @param ingredients Ingredients to replace the list with.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setIngredients(ingredients);
    }

    /**
     * Replaces the contents of the orders list with {@code orders}
     * {@code orders} must not contain duplicate orders.
     *
     * @param orders Orders to replace the list with.
     */
    public void setOrders(List<Order> orders) {
        this.orders.setOrders(orders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setIngredients(newData.getIngredientList());
        setOrders(newData.getOrderList());
    }

    //// client-level operations

    /**
     * Returns true if a client with the same identity as {@code client} exists in the address book.
     */
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clients.contains(client);
    }

    /**
     * Adds a client to the address book.
     * The client must not already exist in the address book.
     */
    public void addClient(Client c) {
        clients.add(c);
    }

    /**
     * Replaces the given client {@code target} in the list with {@code editedClient}.
     * {@code target} must exist in the address book.
     * The client identity of {@code editedClient} must not be the same as another existing client in the address book.
     */
    public void setClient(Client target, Client editedClient) throws NotFoundException {
        requireNonNull(editedClient);
        clients.setClient(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeClient(Client key) throws NotFoundException {
        clients.remove(key);
    }

    //// ingredient-level operations

    /**
     * Adds an ingredient to the address book.
     * The ingredient must not already exist in the address book.
     *
     * @param ingredient Ingredient to be added.
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Returns true if an ingredient with the same identity as {@code ingredient} exists in the address book.
     *
     * @param ingredient to check.
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredients.contains(ingredient);
    }

    /**
     * Replaces the similar ingredient that is in the address book with a new ingredient whose quantity is increased
     * by the quantity in {@code target} if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    public void addIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);
        ingredients.addIngredientQuantity(target, multiplier);
    }

    /**
     * Replaces the similar ingredient that is in the address book with a new ingredient whose quantity is reduced
     * by the quantity in {@code target} if it exists.
     *
     * @param target The target ingredient.
     * @param multiplier The multiplier.
     */
    public void minusIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);
        ingredients.minusIngredientQuantity(target, multiplier);
    }

    /**
     * Removes the given ingredient from the address book.
     * The given ingredient must exist in the address book.
     *
     * @param ingredientToRemove The ingredient to remove from the ingredients list.
     * @throws NotFoundException when the given ingredient does not exist in the ingredients list.
     */
    public void removeIngredient(Ingredient ingredientToRemove) throws NotFoundException {
        requireNonNull(ingredientToRemove);
        ingredients.remove(ingredientToRemove);
    }

    /**
     * Replaces the existing target Ingredient in the address book with an edited Ingredient.
     *
     * @param target The target ingredient to replace.
     * @param editedIngredient The edited ingredient to replace with.
     * @throws NotFoundException if the target ingredient does not exist in the address book.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) throws NotFoundException {
        requireAllNonNull(target, editedIngredient);
        ingredients.setIngredient(target, editedIngredient);
    }

    //// order-level operations

    /**
     * Adds an order to the address book.
     * The order must not already exist in the address book.
     *
     * @param order Order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Returns true if an order with the same identity as {@code order} exists in the address book.
     */
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orders.contains(order);
    }

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     *
     * @param orderToRemove The order to remove from the orders list.
     * @throws NotFoundException when the given order does not exist in the orders list.
     */
    public void removeOrder(Order orderToRemove) throws NotFoundException {
        requireNonNull(orderToRemove);
        orders.remove(orderToRemove);
    }

    /** Replaces the existing target Order in the address book with an edited Order.
     *
     * @param target The target Order to replace.
     * @param editedOrder The edited order to replace with.
     * @throws NotFoundException If the target order does not exist in the address book.
     */
    public void setOrder(Order target, Order editedOrder) throws NotFoundException {
        requireAllNonNull(target, editedOrder);
        orders.setOrder(target, editedOrder);
    }

    //// statistics-level operations

    /**
     * Returns the top 10 clients with the most orders.
     *
     * @return List containing the top 10 clients with most orders.
     */
    public List<Entry<OrderClient, Long>> getTopTenOrderClients() {
        return orders.getTopTenOrderClients();
    }

    //// util methods

    @Override
    public String toString() {
        return clients.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && clients.equals(((AddressBook) other).clients));
    }

    @Override
    public int hashCode() {
        return clients.hashCode();
    }
}
