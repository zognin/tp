package ay2122s1_cs2103t_w16_2.btbb.model.order;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Represents a container that consists of an order's client name and phone number.
 */
public class OrderClient {
    private GenericString name;
    private Phone phone;

    /**
     * Constructs an orderClient object.
     *
     * @param name Client's name tagged to order.
     * @param phone Client's phone tagged to order.
     */
    public OrderClient(GenericString name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    public GenericString getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OrderClient)) {
            return false;
        }

        OrderClient otherOrderClient = (OrderClient) other;

        return otherOrderClient.getName().equals(getName())
                && otherOrderClient.getPhone().equals(getPhone());
    }
}
