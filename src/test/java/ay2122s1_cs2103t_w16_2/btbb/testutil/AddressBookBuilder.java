package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withClient("John", "Doe").build();}
 */
public class AddressBookBuilder {
    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Client} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withClient(Client client) {
        addressBook.addClient(client);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
