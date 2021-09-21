package seedu.address.logic.parser.booking;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.util.ParserUtil.arePrefixesPresent;

import seedu.address.exception.ParseException;
import seedu.address.logic.commands.booking.AddBookingCommand;
import seedu.address.logic.descriptors.BookingDescriptor;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;

public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    @Override
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }

        BookingDescriptor bookingDescriptor = new BookingDescriptor();
        bookingDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));

        return new AddBookingCommand(bookingDescriptor);
    }
}
