package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that always accepts the order being added.
 */
public class ModelStubAcceptingOrderAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();
    private final ArrayList<Order> ordersAdded = new ArrayList<>();
    private final ArrayList<Recipe> recipeAdded = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        requireNonNull(client);
        clientsAdded.add(client);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        ingredientsAdded.add(ingredient);
    }

    @Override
    public void addOrder(Order order) {
        requireNonNull(order);
        ordersAdded.add(order);
    }

    @Override
    public void deleteOrder(Order orderToDelete) {
        ordersAdded.remove(orderToDelete);
    }

    @Override
    public void setOrder(Order order, Order editedOrder) throws NotFoundException {
        requireAllNonNull(order, editedOrder);

        int index = ordersAdded.indexOf(order);
        if (index == -1) {
            throw new NotFoundException(Order.class.getName());
        }

        ordersAdded.set(index, editedOrder);
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableList(clientsAdded);
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return FXCollections.observableList(ordersAdded);
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return FXCollections.observableList(recipeAdded);
    }

    public ArrayList<Order> getOrdersAdded() {
        return ordersAdded;
    }

    public ArrayList<Ingredient> getIngredientsAdded() {
        return ingredientsAdded;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return ordersAdded.stream().anyMatch(order::isSameOrder);
    }

    @Override
    public void addIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);

        Ingredient currentIngredient = ingredientsAdded.stream()
                .filter(target::isSameIngredient).findFirst().orElse(null);

        if (currentIngredient != null) {
            Ingredient ingredientWithNewQuantity = new Ingredient(
                    currentIngredient.getName(),
                    currentIngredient.getQuantity().addQuantityBy(target.getQuantity(), multiplier),
                    currentIngredient.getUnit());

            int index = ingredientsAdded.indexOf(currentIngredient);
            ingredientsAdded.set(index, ingredientWithNewQuantity);
        }
    }

    @Override
    public void minusIngredientQuantity(Ingredient target, Quantity multiplier) {
        requireAllNonNull(target, multiplier);

        Ingredient currentIngredient = ingredientsAdded.stream()
                .filter(target::isSameIngredient).findFirst().orElse(null);

        if (currentIngredient != null) {
            Ingredient ingredientWithNewQuantity = new Ingredient(
                    currentIngredient.getName(),
                    currentIngredient.getQuantity().minusQuantityBy(target.getQuantity(), multiplier),
                    currentIngredient.getUnit());

            int index = ingredientsAdded.indexOf(currentIngredient);
            ingredientsAdded.set(index, ingredientWithNewQuantity);
        }
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {}

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
