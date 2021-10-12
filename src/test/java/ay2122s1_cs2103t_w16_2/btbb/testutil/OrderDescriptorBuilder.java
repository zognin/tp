package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.ArrayList;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class to help with building OrderDescriptorBuilder objects.
 */
public class OrderDescriptorBuilder {
    private OrderDescriptor descriptor;

    /**
     * Creates an empty {@code OrderDescriptorBuilder}.
     */
    public OrderDescriptorBuilder() {
        descriptor = new OrderDescriptor();
    }

    /**
     * Initializes the OrderDescriptorBuilder with the data of {@code descriptor}.
     *
     * @param descriptor The data from which to copy from to create a new order descriptor.
     */
    public OrderDescriptorBuilder(OrderDescriptor descriptor) {
        this.descriptor = new OrderDescriptor(descriptor);
    }

    /**
     * Returns a {@code OrderDescriptor} with fields containing {@code order}'s details
     *
     * @param order The order from which to copy from to create a new order descriptor.
     */
    public OrderDescriptorBuilder(Order order) {
        descriptor = new OrderDescriptor();
        descriptor.setClientName(order.getClientName());
        descriptor.setClientPhone(order.getClientPhone());
        descriptor.setClientAddress(order.getClientAddress());
        descriptor.setRecipeName(order.getRecipeName());
        descriptor.setRecipeIngredients(order.getRecipeIngredients());
        descriptor.setPrice(order.getPrice());
        descriptor.setDeadline(order.getDeadline());
        descriptor.setQuantity(order.getQuantity());
        descriptor.setCompletionStatus(order.getCompletionStatus());
    }

    /**
     * Sets the {@code Name} of the {@code OrderDescriptor} that we are building.
     *
     * @param clientName The name that should be set.
     * @return A OrderDescriptorBuilder object that contains the new name details.
     */
    public OrderDescriptorBuilder withClientName(String clientName) {
        descriptor.setClientName(new GenericString(clientName));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code OrderDescriptor} that we are building.
     *
     * @param clientPhone The phone that should be set.
     * @return A OrderDescriptorBuilder object that contains the new phone details.
     */
    public OrderDescriptorBuilder withClientPhone(String clientPhone) {
        descriptor.setClientPhone(new Phone(clientPhone));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code OrderDescriptor} that we are building.
     *
     * @param clientAddress The address that should be set.
     * @return A OrderDescriptorBuilder object that contains the new address details.
     */
    public OrderDescriptorBuilder withClientAddress(String clientAddress) {
        descriptor.setClientAddress(new Address(clientAddress));
        return this;
    }

    /**
     * Sets the {@code Index} of the {@code OrderDescriptor} that we are building.
     *
     * @param clientIndex The index that should be set.
     * @return A OrderDescriptorBuilder object that contains the new index details.
     */
    public OrderDescriptorBuilder withClientIndex(Index clientIndex) {
        descriptor.setClientIndex(clientIndex);
        return this;
    }

    /**
     * Sets the {@code recipeName} of the {@code OrderDescriptor} that we are building.
     *
     * @param recipeName The recipeName that should be set.
     * @return A OrderDescriptorBuilder object that contains the new recipeName details.
     */
    public OrderDescriptorBuilder withRecipeName(String recipeName) {
        descriptor.setRecipeName(new GenericString(recipeName));
        return this;
    }

    /**
     * Sets the {@code ingredients} of the {@code OrderDescriptor} that we are building.
     *
     * @param ingredients The ingredients that should be set.
     * @return A OrderDescriptorBuilder object that contains the new ingredients details.
     */
    public OrderDescriptorBuilder withRecipeIngredients(List<Ingredient> ingredients) {
        if (ingredients.isEmpty()) {
            descriptor.setRecipeIngredients(new RecipeIngredientList(new ArrayList<>()));
            return this;
        }
        descriptor.setRecipeIngredients(new RecipeIngredientList(ingredients));
        return this;
    }

    /**
     * Sets the {@code price} of the {@code OrderDescriptor} that we are building.
     *
     * @param price The price that should be set.
     * @return A OrderDescriptorBuilder object that contains the new price details.
     */
    public OrderDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code deadline} of the {@code OrderDescriptor} that we are building.
     *
     * @param deadline The deadline that should be set.
     * @return A OrderDescriptorBuilder object that contains the new deadline details.
     */
    public OrderDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code quantity} of the {@code OrderDescriptor} that we are building.
     *
     * @param quantity The quantity that should be set.
     * @return A OrderDescriptorBuilder object that contains the new quantity details.
     */
    public OrderDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code quantity} of the {@code OrderDescriptor} that we are building.
     *
     * @param completionStatus The quantity that should be set.
     * @return A OrderDescriptorBuilder object that contains the new quantity details.
     */
    public OrderDescriptorBuilder withCompletionStatus(String completionStatus) {
        descriptor.setCompletionStatus(new CompletionStatus(completionStatus));
        return this;
    }

    public OrderDescriptor build() {
        return descriptor;
    }
}
