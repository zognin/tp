package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Represents a Booking in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {
    private final Person person;

    /**
     * Constructs a booking.
     *
     * @param person Person to whom this booking belongs to.
     */
    public Booking(Person person) {
        requireAllNonNull(person);
        this.person = person;
    }

    private Person getPerson() {
        return person;
    }

    public Phone getPhone() {
        return getPerson().getPhone();
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
        return otherBooking.getPerson().equals(getPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Phone: ")
                .append(getPhone());

        return builder.toString();
    }
}
