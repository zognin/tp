package seedu.btbb.logic.parser.booking;

import static seedu.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.btbb.exception.ParseException;
import seedu.btbb.logic.commands.booking.AddBookingCommand;
import seedu.btbb.logic.descriptors.BookingDescriptor;
import seedu.btbb.logic.parser.Parser;
import seedu.btbb.logic.parser.util.ArgumentMultimap;
import seedu.btbb.logic.parser.util.ArgumentTokenizer;
import seedu.btbb.logic.parser.util.ParserUtil;

public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    @Override
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PHONE);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_PHONE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }

        BookingDescriptor bookingDescriptor = new BookingDescriptor();
        bookingDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));

        return new AddBookingCommand(bookingDescriptor);
    }
}
