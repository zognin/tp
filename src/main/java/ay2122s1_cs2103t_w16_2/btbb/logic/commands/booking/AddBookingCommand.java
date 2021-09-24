package ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;

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
