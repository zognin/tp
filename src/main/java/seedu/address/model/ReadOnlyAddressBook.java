package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.client.Client;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {
    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    /**
     * Returns an unmodifiable view of the bookings list.
     * This list will not contain any duplicate bookings.
     */
    ObservableList<Booking> getBookingList();
}
