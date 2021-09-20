package seedu.address.logic.commands.booking;

import java.util.Optional;

import seedu.address.logic.commands.client.EditCommand;
import seedu.address.model.booking.Booking;
import seedu.address.model.client.Client;

/**
 * Edits the details of an existing client in the address book.
 */
public class EditBookingCommand {
    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToEdit}
     * @param bookingToEdit Booking to be edited.
     * @param editBookingDescriptor Params of for the new booking.
     * @return {@code Booking}.
     */
    public static Booking createEditedBooking(Booking bookingToEdit, EditBookingDescriptor editBookingDescriptor) {
        assert bookingToEdit != null;

        Client updatedClient = editBookingDescriptor.getClient().orElse(bookingToEdit.getClient());

        return new Booking(updatedClient);
    }

    /**
     * Stores the details to edit the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class EditBookingDescriptor {
        private Client client;

        public EditBookingDescriptor() {}

        public void setClient(Client client) {
            this.client = client;
        }

        private Optional<Client> getClient() {
            return Optional.ofNullable(client);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditClientDescriptor)) {
                return false;
            }

            // state check
            EditBookingDescriptor e = (EditBookingDescriptor) other;

            return getClient().equals(e.getClient());
        }
    }
}
