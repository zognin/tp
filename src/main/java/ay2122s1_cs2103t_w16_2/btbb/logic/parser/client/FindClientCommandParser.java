package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.Prefix;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.AddressContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.EmailContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.NameContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {
    private void addClientPredicate(ClientPredicateCollection clientPredicateCollection, ArgumentMultimap argMultimap,
                                    Prefix prefix, Function<List<String>, Predicate<Client>> predicateFunction) {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return;
        }

        String trimmedSearchArg = argMultimap.getValue(prefix).get().trim();
        if (trimmedSearchArg.isEmpty()) {
            return;
        }

        List<String> keywords = List.of(trimmedSearchArg.split("\\s+"));
        Predicate<Client> clientPredicate = predicateFunction.apply(keywords);
        clientPredicateCollection.addClientPredicate(clientPredicate);
    }

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

        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();

        addClientPredicate(clientPredicateCollection, argMultimap,
                           PREFIX_CLIENT_NAME, NameContainsKeywordsPredicate::new);
        addClientPredicate(clientPredicateCollection, argMultimap,
                           PREFIX_CLIENT_EMAIL, EmailContainsKeywordsPredicate::new);
        addClientPredicate(clientPredicateCollection, argMultimap,
                           PREFIX_CLIENT_PHONE, PhoneContainsKeywordsPredicate::new);
        addClientPredicate(clientPredicateCollection, argMultimap,
                           PREFIX_CLIENT_ADDRESS, AddressContainsKeywordsPredicate::new);

        if (clientPredicateCollection.hasNoPredicates()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        return new FindClientCommand(clientPredicateCollection);
    }
}
