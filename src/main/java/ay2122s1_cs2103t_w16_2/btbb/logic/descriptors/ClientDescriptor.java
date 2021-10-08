package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Stores the details to relevant to a client.
 * Some details have to be converted to their model representations before converting to a Client model type.
 */
public class ClientDescriptor {
    private GenericString name;
    private Phone phone;
    private Email email;
    private Address address;

    public ClientDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public ClientDescriptor(ClientDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address);
    }

    public void setName(GenericString name) {
        this.name = name;
    }

    public Optional<GenericString> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Converts a Client Descriptor to a Client model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Client}.
     */
    public Client toModelType() {
        requireAllNonNull(name, phone, email, address);
        return new Client(name, phone, email, address);
    }

    /**
     * Converts a Client Descriptor to a Client model type.
     * Missing fields are filled with an existing client.
     *
     * @param existingClient An existing Client that is not null.
     * @return {@code Client}.
     */
    public Client toModelTypeFrom(Client existingClient) {
        assert existingClient != null;

        GenericString updatedName = getName().orElse(existingClient.getName());
        Phone updatedPhone = getPhone().orElse(existingClient.getPhone());
        Email updatedEmail = getEmail().orElse(existingClient.getEmail());
        Address updatedAddress = getAddress().orElse(existingClient.getAddress());

        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientDescriptor)) {
            return false;
        }

        // state check
        ClientDescriptor e = (ClientDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress());
    }
}
