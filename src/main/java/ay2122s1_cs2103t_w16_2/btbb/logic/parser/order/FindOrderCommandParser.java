package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.FindOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

/**
 * Parses input arguments and creates a new FindOrderCommand object.
 */
public class FindOrderCommandParser implements Parser<FindOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindOrderCommand
     * and returns a FindOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindOrderCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap
                argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_ADDRESS);

        PredicateCollection<Order> predicateCollection = new PredicateCollection<>();
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_NAME, argMultimap, Order::getClientName
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_PHONE, argMultimap, Order::getClientPhone
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_ADDRESS, argMultimap, Order::getClientAddress
        );

        if (predicateCollection.hasNoPredicates() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
        }

        return new FindOrderCommand(predicateCollection);
    }
}
