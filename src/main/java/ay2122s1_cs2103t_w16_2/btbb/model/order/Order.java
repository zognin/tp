package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order implements Comparable<Order> {
    private final GenericString clientName;
    private final Phone clientPhone;
    private final Address clientAddress;
    private final GenericString recipeName;
    private final RecipeIngredientList recipeIngredients;
    private final OrderPrice orderPrice;
    private final Deadline deadline;
    private final Quantity quantity;
    private final CompletionStatus completionStatus;

    /**
     * Constructs an order object.
     *
     * @param clientName The client's name.
     * @param clientPhone The client's phone number.
     * @param clientAddress The client's address.
     * @param recipeName The name of the recipe chosen.
     * @param recipeIngredients The list of ingredients used the for recipe in the order.
     * @param orderPrice The orderPrice of the order.
     * @param deadline The deadline for this order.
     * @param quantity The quantity of this order.
     */
    public Order(GenericString clientName, Phone clientPhone, Address clientAddress,
                 GenericString recipeName, RecipeIngredientList recipeIngredients, OrderPrice orderPrice,
                 Deadline deadline, Quantity quantity, CompletionStatus completionStatus) {
        requireAllNonNull(clientName, clientPhone, clientAddress,
                recipeName, recipeIngredients, recipeName, deadline, quantity, completionStatus);
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.orderPrice = orderPrice;
        this.deadline = deadline;
        this.quantity = quantity;
        this.completionStatus = completionStatus;
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

    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public LocalDate getDeadlineDate() {
        return deadline.getDeadline().toLocalDate();
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public int getQuantityAsInt() {
        return quantity.getQuantityAsInt();
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public OrderClient getOrderClient() {
        return new OrderClient(getClientName(), getClientPhone());
    }

    public YearMonth getYearMonth() {
        return YearMonth.of(deadline.getYear(), deadline.getMonth());
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
                && otherOrder.getOrderPrice().equals(getOrderPrice())
                && otherOrder.getDeadline().equals(getDeadline());
    }

    @Override
    public int compareTo(Order other) {
        return Comparator.comparing(Order::getCompletionStatus)
                .thenComparing(Order::getDeadline)
                .compare(this, other);
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
                && otherOrder.getOrderPrice().equals(getOrderPrice())
                && otherOrder.getDeadline().equals(getDeadline())
                && otherOrder.getQuantity().equals(getQuantity())
                && otherOrder.getCompletionStatus().equals(getCompletionStatus());
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
                .append("\n")
                .append("Recipe Name: ")
                .append(getRecipeName())
                .append("; Recipe Ingredients: ")
                .append(getRecipeIngredients())
                .append("\n")
                .append("Order Price: ")
                .append(getOrderPrice())
                .append("; Order Deadline: ")
                .append(getDeadline())
                .append("; Order Quantity: ")
                .append(getQuantity())
                .append("; Order Status: ")
                .append(getCompletionStatus().getDisplayMessage());

        return builder.toString();
    }

    /**
     * Gets the string representation of an Order object, without CompletionStatus attribute.
     * For use in Done and Undone commands only.
     *
     * @return String representation of Order object, without CompletionStatus attribute.
     */
    public String toStringWithoutCompletionStatus() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Client Name: ")
                .append(getClientName())
                .append("; Client Phone: ")
                .append(getClientPhone())
                .append("; Client Address: ")
                .append(getClientAddress())
                .append("\n")
                .append("Recipe Name: ")
                .append(getRecipeName())
                .append("; Recipe Ingredients: ")
                .append(getRecipeIngredients())
                .append("\n")
                .append("Order Price: ")
                .append(getOrderPrice())
                .append("; Order Deadline: ")
                .append(getDeadline())
                .append("; Order Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }
}
