package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
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
        descriptor.setPhone(order.getClientPhone());
    }

    /**
     * Sets the {@code Phone} of the {@code OrderDescriptor} that we are building.
     *
     * @param phone The phone that should be set.
     * @return A OrderDescriptorBuilder object that contains the new phone details.
     */
    public OrderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    public OrderDescriptor build() {
        return descriptor;
    }
}
