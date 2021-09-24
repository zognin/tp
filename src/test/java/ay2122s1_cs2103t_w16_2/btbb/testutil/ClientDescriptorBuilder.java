package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * A utility class to help with building EditClientDescriptor objects.
 */
public class ClientDescriptorBuilder {
    private ClientDescriptor descriptor;

    public ClientDescriptorBuilder() {
        descriptor = new ClientDescriptor();
    }

    public ClientDescriptorBuilder(ClientDescriptor descriptor) {
        this.descriptor = new ClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details
     */
    public ClientDescriptorBuilder(Client client) {
        descriptor = new ClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditClientDescriptor} that we are building.
     */
    public ClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditClientDescriptor} that we are building.
     */
    public ClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditClientDescriptor} that we are building.
     */
    public ClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditClientDescriptor} that we are building.
     */
    public ClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public ClientDescriptor build() {
        return descriptor;
    }
}
