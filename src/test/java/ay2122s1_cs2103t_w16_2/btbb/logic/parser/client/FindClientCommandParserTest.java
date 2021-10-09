package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_NAME_ALICE_BOB_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_PHONE_9427_3217_PREDICATE;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

public class FindClientCommandParserTest {
    private FindClientCommandParser parser = new FindClientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKeywords_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_CLIENT_NAME + " " + PREFIX_CLIENT_ADDRESS
                + " " + PREFIX_CLIENT_EMAIL + " " + PREFIX_CLIENT_PHONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindClientCommand() {
        PredicateCollection<Client> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(CLIENT_NAME_ALICE_BOB_PREDICATE);
        predicateCollection.addPredicate(CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE);
        predicateCollection.addPredicate(CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE);
        predicateCollection.addPredicate(CLIENT_PHONE_9427_3217_PREDICATE);
        FindClientCommand expectedFindClientCommand = new FindClientCommand(predicateCollection);

        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Alice Bob "
                + PREFIX_CLIENT_ADDRESS + "Yishun Geylang " + PREFIX_CLIENT_PHONE + "9427 3217 "
                + PREFIX_CLIENT_EMAIL + "alice@gmail.com bob@gmail.com", expectedFindClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Alice \n \t Bob "
                + PREFIX_CLIENT_ADDRESS + "Yishun  \n \t Geylang " + PREFIX_CLIENT_PHONE + "9427 \n \t 3217 "
                + PREFIX_CLIENT_EMAIL + "alice@gmail.com  \n \t bob@gmail.com", expectedFindClientCommand);
    }
}
