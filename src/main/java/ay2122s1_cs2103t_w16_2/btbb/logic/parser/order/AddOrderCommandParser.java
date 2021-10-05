package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand.MESSAGE_USAGE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    @Override
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT, PREFIX_CLIENT_PHONE,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_ADDRESS);

        if ((!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_CLIENT)
                && !ParserUtil.areAllPrefixesPresent(argMultimap,
                                                    PREFIX_CLIENT_PHONE, PREFIX_CLIENT_NAME, PREFIX_CLIENT_ADDRESS))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE)
            );
        }
        OrderDescriptor orderDescriptor = new OrderDescriptor();
        if (argMultimap.getValue(PREFIX_CLIENT).isPresent()) {
            orderDescriptor.setClientIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLIENT).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            orderDescriptor.setClientName(ParserUtil.parseName(argMultimap.getValue(PREFIX_CLIENT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_PHONE).isPresent()) {
            orderDescriptor.setClientPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ADDRESS).isPresent()) {
            orderDescriptor
                    .setClientAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_CLIENT_ADDRESS).get()));
        }

        return new AddOrderCommand(orderDescriptor);
    }
}
