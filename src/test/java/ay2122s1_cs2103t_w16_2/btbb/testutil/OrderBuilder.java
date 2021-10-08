package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {
    private static final String DEFAULT_CLIENT_NAME = "Amy Bee";
    private static final String DEFAULT_CLIENT_PHONE = "85355255";
    private static final String DEFAULT_CLIENT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private GenericString clientName;
    private Phone clientPhone;
    private Address clientAddress;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        clientName = new GenericString(DEFAULT_CLIENT_NAME);
        clientPhone = new Phone(DEFAULT_CLIENT_PHONE);
        clientAddress = new Address(DEFAULT_CLIENT_ADDRESS);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     *
     * @param orderToCopy The data from which to copy from to create a new order.
     */
    public OrderBuilder(Order orderToCopy) {
        clientName = orderToCopy.getClientName();
        clientPhone = orderToCopy.getClientPhone();
        clientAddress = orderToCopy.getClientAddress();
    }

    /**
     * Sets the {@code clientName} of the {@code OrderBuilder} that we are building.
     *
     * @param clientName The client's name associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientName(GenericString clientName) {
        this.clientName = clientName;
        return this;
    }

    /**
     * Sets the {@code clientPhone} of the {@code OrderBuilder} that we are building.
     *
     * @param clientPhone The client's phone associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientPhone(Phone clientPhone) {
        this.clientPhone = clientPhone;
        return this;
    }

    /**
     * Sets the {@code clientAddress} of the {@code OrderBuilder} that we are building.
     *
     * @param clientAddress The client's address associated with the order we are building.
     * @return The {@code OrderBuilder} object.
     */
    public OrderBuilder withClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
        return this;
    }

    public Order build() {
        return new Order(clientName, clientPhone, clientAddress);
    }
}
