package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.AddressContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.EmailContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.NameContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.PhoneContainsKeywordsPredicate;

public class FindClientCommandParserTest {
    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindClientCommand() {
        // no leading and trailing whitespaces
        ClientPredicateCollection clientPredicateCollection = new ClientPredicateCollection();
        clientPredicateCollection.addClientPredicate(
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        clientPredicateCollection.addClientPredicate(
                new AddressContainsKeywordsPredicate(Arrays.asList("Yishun", "Geylang")));
        clientPredicateCollection.addClientPredicate(
                new EmailContainsKeywordsPredicate(Arrays.asList("alice@gmail.com", "bob@gmail.com")));
        clientPredicateCollection.addClientPredicate(
                new PhoneContainsKeywordsPredicate(Arrays.asList("94573217", "82162616")));
        FindClientCommand expectedFindClientCommand = new FindClientCommand(clientPredicateCollection);
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Alice Bob "
                + PREFIX_CLIENT_ADDRESS + "Yishun Geylang " + PREFIX_CLIENT_PHONE + "94573217 82162616 "
                + PREFIX_CLIENT_EMAIL + "alice@gmail.com bob@gmail.com", expectedFindClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Alice \n \t Bob "
                + PREFIX_CLIENT_ADDRESS + "Yishun  \n \t Geylang " + PREFIX_CLIENT_PHONE + "94573217  \n \t 82162616 "
                + PREFIX_CLIENT_EMAIL + "alice@gmail.com  \n \t bob@gmail.com", expectedFindClientCommand);
    }
}
