package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientComboPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.NameContainsKeywordsPredicate;

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
        ClientComboPredicate clientComboPredicate = new ClientComboPredicate();
        clientComboPredicate.addClientPredicate(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        FindClientCommand expectedFindClientCommand = new FindClientCommand(clientComboPredicate);
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Alice Bob", expectedFindClientCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME
                + " \n Alice \n \t Bob  \t", expectedFindClientCommand);
    }
}
