package ay2122s1_cs2103t_w16_2.btbb.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedBooking> bookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given clients.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("bookings") List<JsonAdaptedBooking> bookings) {
        this.clients.addAll(clients);
        this.bookings.addAll(bookings);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        bookings.addAll(source.getBookingList().stream().map(JsonAdaptedBooking::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }

        // Convert clients to model type before bookings
        // As bookings are dependent on clients
        for (JsonAdaptedBooking jsonAdaptedBooking : bookings) {
            Booking booking = jsonAdaptedBooking.toModelType(addressBook);
            addressBook.addBooking(booking);
        }

        return addressBook;
    }
}
