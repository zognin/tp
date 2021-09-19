package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.client.Client;
import seedu.address.model.client.Phone;

/**
 * Jackson-friendly version of {@link Booking}.
 */
public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("phone") String phone) {
        this.phone = phone;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        phone = source.getPhone().value;
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @param addressBook Address book to find clients from.
     * @return Model type booking.
     * @throws IllegalValueException If there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType(AddressBook addressBook) throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        Optional<Client> modelClient = addressBook.getClientByPhone(modelPhone);
        if (!(modelClient.isPresent())) {
            throw new IllegalValueException(Booking.MESSAGE_CONSTRAINTS);
        }

        return new Booking(modelClient.get());
    }
}
