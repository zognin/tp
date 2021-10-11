package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindClientCommand
     * and returns a FindClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClientCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ADDRESS);

        PredicateCollection<Client> predicateCollection = new PredicateCollection<>();
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_NAME, argMultimap, Client::getName
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_ADDRESS, argMultimap, Client::getAddress
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_PHONE, argMultimap, Client::getPhone
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_CLIENT_EMAIL, argMultimap, Client::getEmail
        );

        if (predicateCollection.hasNoPredicates() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        return new FindClientCommand(predicateCollection);
    }
}
