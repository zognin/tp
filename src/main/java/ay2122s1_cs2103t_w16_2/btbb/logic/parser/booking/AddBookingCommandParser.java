package ay2122s1_cs2103t_w16_2.btbb.logic.parser.booking;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking.AddBookingCommand.MESSAGE_USAGE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking.AddBookingCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    @Override
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PHONE);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_PHONE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE)
            );
        }

        BookingDescriptor bookingDescriptor = new BookingDescriptor();
        bookingDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));

        return new AddBookingCommand(bookingDescriptor);
    }
}
