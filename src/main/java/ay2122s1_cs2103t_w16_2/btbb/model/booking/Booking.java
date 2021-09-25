package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * Represents a Booking in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {
    private final Client client;

    /**
     * Constructs a booking.
     *
     * @param client Person to whom this booking belongs to.
     */
    public Booking(Client client) {
        requireAllNonNull(client);
        this.client = client;
    }

    private Client getClient() {
        return client;
    }

    public Name getName() {
        return getClient().getName();
    }

    public Phone getPhone() {
        return getClient().getPhone();
    }

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
