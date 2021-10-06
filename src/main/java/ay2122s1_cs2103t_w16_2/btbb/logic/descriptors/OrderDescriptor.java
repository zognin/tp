package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

public class OrderDescriptor {
    public static final String MESSAGE_MISSING_CLIENT_DETAILS = "Both client and client details cannot be found";

    private Index clientIndex;
    private Phone clientPhone;
    private Name clientName;
    private Address clientAddress;

    public OrderDescriptor() {};

    /**
     * Constructs an {@code OrderDescriptor} using the details of an existing {@code OrderDescriptor}.
     *
     * @param toCopy Existing {@code OrderDescriptor}.
     */
    public OrderDescriptor(OrderDescriptor toCopy) {
        setClientIndex(toCopy.clientIndex);
        setClientPhone(toCopy.clientPhone);
        setClientName(toCopy.clientName);
        setClientAddress(toCopy.clientAddress);
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
     *
     * @param model To get the client list.
     * @return {@code Order}
     * @throws CommandException if both the client and its details are missing.
     */
    public Order toModelType(Model model) throws CommandException {
        List<Client> clientList = model.getFilteredClientList();
        Optional<Client> client = getClientIndex().isPresent()
                ? Optional.of(clientList.get(clientIndex.getZeroBased()))
                : Optional.empty();

        try {
            Name clientName = getClientName().orElseGet(() -> client.get().getName());
            Phone clientPhone = getClientPhone().orElseGet(() -> client.get().getPhone());
            Address clientAddress = getClientAddress().orElseGet(() -> client.get().getAddress());
            return new Order(clientPhone, clientName, clientAddress);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_MISSING_CLIENT_DETAILS);
        }
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
