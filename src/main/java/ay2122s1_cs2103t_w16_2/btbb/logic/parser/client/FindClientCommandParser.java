package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand.MESSAGE_NOT_FOUND;
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
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientComboPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.EmailContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.NameContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {
    private void addClientPredicate(ClientComboPredicate clientComboPredicate, ArgumentMultimap argMultimap,
                                    Prefix prefix, Function<List<String>, Predicate<Client>> predicateFunction) {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return;
        }

        List<String> keywords = List.of(argMultimap.getValue(prefix).get().trim().split("\\s+"));
        Predicate<Client> clientPredicate = predicateFunction.apply(keywords);
        clientComboPredicate.addClientPredicate(clientPredicate);
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

        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();

        addClientPredicate(clientComboPredicate, argMultimap,
                           PREFIX_CLIENT_NAME, NameContainsKeywordsPredicate::new);
        addClientPredicate(clientComboPredicate, argMultimap,
                           PREFIX_CLIENT_EMAIL, EmailContainsKeywordsPredicate::new);
        addClientPredicate(clientComboPredicate, argMultimap,
                           PREFIX_CLIENT_PHONE, PhoneContainsKeywordsPredicate::new);
        addClientPredicate(clientComboPredicate, argMultimap,
                           PREFIX_CLIENT_ADDRESS, AddressContainsKeywordsPredicate::new);

        if (clientComboPredicate.hasNoPredicate()) {
            throw new ParseException(MESSAGE_NOT_FOUND);
        }

        return new FindClientCommand(clientComboPredicate);
    }
}
