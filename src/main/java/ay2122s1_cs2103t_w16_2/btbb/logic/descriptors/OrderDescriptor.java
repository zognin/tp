package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

public class OrderDescriptor {
    private Index clientIndex;
    private Phone clientPhone;
    private Name clientName;
    private Address clientAddress;

    public OrderDescriptor() {};

    public OrderDescriptor(OrderDescriptor toCopy) {
        setClientPhone(toCopy.clientPhone);
    }

    public void setClientPhone(Phone clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Optional<Phone> getClientPhone() {
        return Optional.ofNullable(clientPhone);
    }

    public void setClientIndex(Index clientIndex) {
        this.clientIndex = clientIndex;
    }

    public Optional<Index> getClientIndex() {
        return Optional.ofNullable(clientIndex);
    }

    public void setClientName(Name clientName) {
        this.clientName = clientName;
    }

    public Optional<Name> getClientName() {
        return Optional.ofNullable(clientName);
    }

    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Optional<Address> getClientAddress() {
        return Optional.ofNullable(clientAddress);
    }

    /**
     * Converts an Order Descriptor to an Order model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Order}.
     */
    public Order toModelType(Model model) {
        requireAllNonNull(clientPhone);
        List<Client> clientList = model.getFilteredClientList();
        Client client = getClientIndex().isPresent() ? clientList.get(clientIndex.getZeroBased()) : null;
        Name clientName = getClientName().orElse(client.getName());
        Phone clientPhone = getClientPhone().orElse(client.getPhone());
        Address clientAddress = getClientAddress().orElse(client.getAddress());
        return new Order(clientPhone, clientName, clientAddress);
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

        return getClientIndex().equals(otherOrderDescriptor.getClientIndex())
                && getClientPhone().equals(otherOrderDescriptor.getClientPhone())
                && getClientAddress().equals(otherOrderDescriptor.getClientAddress())
                && getClientName().equals(otherOrderDescriptor.getClientName());
    }
}
