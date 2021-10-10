package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    private final GenericString clientName;
    private final Phone clientPhone;
    private final Address clientAddress;
    private final GenericString recipeName;
    private final RecipeIngredientList recipeIngredients;
    private final Price price;
    private final Deadline deadline;
    private final Quantity quantity;

    /**
     * Constructs an order object.
     *
     * @param clientName The client's name.
     * @param clientPhone The client's phone number.
     * @param clientAddress The client's address.
     * @param recipeName The name of the recipe chosen.
     * @param recipeIngredients The list of ingredients used the for recipe in the order.
     * @param price The price of the order.
     * @param deadline The deadline for this order.
     * @param quantity The quantity of this order.
     */
    public Order(GenericString clientName, Phone clientPhone, Address clientAddress,
                 GenericString recipeName, RecipeIngredientList recipeIngredients, Price price,
                 Deadline deadline, Quantity quantity) {
        requireAllNonNull(clientName, clientPhone, clientAddress,
                recipeName, recipeIngredients, recipeName, deadline, quantity);
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.price = price;
        this.deadline = deadline;
        this.quantity = quantity;
    }

    public GenericString getClientName() {
        return clientName;
    }

    public Phone getClientPhone() {
        return clientPhone;
    }

    public Address getClientAddress() {
        return clientAddress;
    }

    public GenericString getRecipeName() {
        return recipeName;
    }

    public RecipeIngredientList getRecipeIngredients() {
        return recipeIngredients;
    }

    public Price getPrice() {
        return price;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns true if both orders have the same client fields, recipe fields, deadline and finished status.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.getClientName().equals(getClientName())
                && otherOrder.getClientPhone().equals(getClientPhone())
                && otherOrder.getClientAddress().equals(getClientAddress())
                && otherOrder.getRecipeName().equals(getRecipeName())
                && otherOrder.getRecipeIngredients().equals(getRecipeIngredients())
                && otherOrder.getPrice().equals(getPrice())
                && otherOrder.getDeadline().equals(getDeadline());
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     * This defines a stronger notion of equality between two orders.
     *
     * @param other Other object to compare to.
     * @return True if both orders have the same identity and data fields, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;

        return otherOrder.getClientName().equals(getClientName())
                && otherOrder.getClientPhone().equals(getClientPhone())
                && otherOrder.getClientAddress().equals(getClientAddress())
                && otherOrder.getRecipeName().equals(getRecipeName())
                && otherOrder.getRecipeIngredients().equals(getRecipeIngredients())
                && otherOrder.getPrice().equals(getPrice())
                && otherOrder.getDeadline().equals(getDeadline())
                && otherOrder.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientPhone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client Name: ")
                .append(getClientName())
                .append("; Client Phone: ")
                .append(getClientPhone())
                .append("; Client Address: ")
                .append(getClientAddress())
                .append("; Recipe Name: ")
                .append(getRecipeName())
                .append("; Recipe Ingredients: ")
                .append(getRecipeIngredients())
                .append("; Order Price: ")
                .append(getPrice())
                .append("; Order Deadline: ")
                .append(getDeadline())
                .append("; Order Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }
}
