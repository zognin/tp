package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

public class OrderDescriptor {
    private Phone phone;

    public OrderDescriptor() {};

    public OrderDescriptor(OrderDescriptor toCopy) {
        setPhone(toCopy.phone);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    /**
     * Converts an Order Descriptor to an Order model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Order}.
     */
    public Order toModelType() {
        requireAllNonNull(phone);
        return new Order(phone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderDescriptor)) {
            return false;
        }

        // state check
        OrderDescriptor otherOrderDescriptor = (OrderDescriptor) other;

        return getPhone().equals(otherOrderDescriptor.getPhone());
    }
}
