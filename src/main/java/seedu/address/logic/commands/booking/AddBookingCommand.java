package seedu.address.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Optional;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.client.Client;
import seedu.address.model.client.Phone;

/**
 * Adds a client to the address book.
 */
public class AddBookingCommand extends Command {
    public static final String COMMAND_WORD = "add-booking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a booking to the address book. "
            + "Parameters: "
            + PREFIX_PHONE + "PHONE ";

    public static final String MESSAGE_SUCCESS = "New booking added: %1$s";
    public static final String MESSAGE_CLIENT_NOT_FOUND =
            "There are no clients with this phone number in the address book";

    private final Phone phone;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddBookingCommand(Phone phone) {
        requireNonNull(phone);

        this.phone = phone;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Client> client = model.getClientByPhone(phone);
        if (!(client.isPresent())) {
            throw new CommandException(MESSAGE_CLIENT_NOT_FOUND);
        }
        Booking bookingToAdd = new Booking(client.get());

        model.addBooking(bookingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, bookingToAdd));
    }

}
