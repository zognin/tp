package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.exception.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Booking}.
 */
public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";
    public static final String INVALID_CLIENT_FIELD_MESSAGE_FORMAT = "Booking's %s field should belong to a client";

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
        phone = source.getPhone().toString();
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     * @param addressBook AddressBook.
     * @return Booking model type.
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

        Optional<Person> optionalModelPerson = addressBook.getPersonByPhone(modelPhone);
        if (!(optionalModelPerson.isPresent())) {
            throw new IllegalValueException(
                    String.format(INVALID_CLIENT_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        return new Booking(optionalModelPerson.get());
    }
}
