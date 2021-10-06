package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    private final Phone clientPhone;
    private final Name clientName;
    private final Address clientAddress;

    /**
     * Constructs an order.
     *
     * @param clientPhone Phone number for the order.
     */
    public Order(Phone clientPhone, Name clientName, Address clientAddress) {
        requireAllNonNull(clientPhone, clientName, clientAddress);
        this.clientPhone = clientPhone;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    public Phone getClientPhone() {
        return clientPhone;
    }

    public Name getClientName() {
        return clientName;
    }

    public Address getClientAddress() {
        return clientAddress;
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
                && otherOrder.getClientPhone().equals(getClientPhone())
                && otherOrder.getClientName().equals(getClientName())
                && otherOrder.getClientAddress().equals(getClientAddress());
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
        return otherOrder.getClientPhone().equals(getClientPhone())
                && otherOrder.getClientName().equals(getClientName())
                && otherOrder.getClientAddress().equals(getClientAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientPhone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Phone: ")
                .append(getClientPhone())
                .append(" Name: ")
                .append(getClientName())
                .append(" Address: ")
                .append(getClientAddress());

        return builder.toString();
    }
}
