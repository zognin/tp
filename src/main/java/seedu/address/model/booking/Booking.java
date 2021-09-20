package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.client.Client;
import seedu.address.model.client.Phone;

/**
 * Represents a Booking in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    public static final String MESSAGE_CONSTRAINTS = "Bookings should belong to a client.";

    private final Client client;

    /**
     * Every field must be present and not null.
     */
    public Booking(Client client) {
        requireAllNonNull(client);
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Phone getPhone() {
        return getClient().getPhone();
    }

    /**
     * Returns true if both bookings have the same identity and data fields.
     * This defines a stronger notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getClient().equals(getClient());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(client);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Phone: ")
                .append(getPhone());

        return builder.toString();
    }

}
