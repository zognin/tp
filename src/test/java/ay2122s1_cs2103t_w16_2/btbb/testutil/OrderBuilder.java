package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {
    private static final String DEFAULT_PHONE = "85355255";

    private Phone clientPhone;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        clientPhone = new Phone(DEFAULT_PHONE);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     *
     * @param orderToCopy The data from which to copy from to create a new order.
     */
    public OrderBuilder(Order orderToCopy) {
        clientPhone = orderToCopy.getClientPhone();
    }

    /**
     * Sets the {@code clientPhone} of the {@code OrderBuilder} that we are building.
     *
     * @param clientPhone The client's phone associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withPhone(Phone clientPhone) {
        this.clientPhone = clientPhone;
        return this;
    }

    public Order build() {
        return new Order(clientPhone);
    }
}
