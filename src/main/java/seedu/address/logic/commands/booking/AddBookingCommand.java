package seedu.address.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.address.exception.CommandException;
import seedu.address.exception.NotFoundException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.descriptors.BookingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Adds a booking to the address book.
 */
public class AddBookingCommand extends Command {
    public static final String COMMAND_WORD = "add-booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the address book. "
            + "Parameters: "
            + PREFIX_PHONE + "PHONE ";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_CLIENT_NOT_FOUND =
            "There are no clients with this phone number in the address book";

    private final BookingDescriptor bookingDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddBookingCommand(BookingDescriptor bookingDescriptor) {
        requireNonNull(bookingDescriptor);
        this.bookingDescriptor = bookingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);

            Booking bookingToAdd = bookingDescriptor.toModelType(model);
            model.addBooking(bookingToAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, bookingToAdd));
        } catch (NotFoundException e) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
    }

}
