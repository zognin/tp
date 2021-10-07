package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

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
    }

    /**
     * Sets the {@code Name} of the {@code OrderDescriptor} that we are building.
     *
     * @param clientName The name that should be set.
     * @return A OrderDescriptorBuilder object that contains the new name details.
     */
    public OrderDescriptorBuilder withClientName(String clientName) {
        descriptor.setClientName(new Name(clientName));
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

    public OrderDescriptor build() {
        return descriptor;
    }
}
