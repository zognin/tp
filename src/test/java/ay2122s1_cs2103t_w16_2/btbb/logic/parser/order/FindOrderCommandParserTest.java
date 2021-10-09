package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_NAME_CAROL_DAVID_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.CLIENT_PHONE_9110_3216_PREDICATE;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.FindOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

public class FindOrderCommandParserTest {
    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKeywords_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_CLIENT_NAME + " " + PREFIX_CLIENT_ADDRESS
                        + " " + PREFIX_CLIENT_PHONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindOrderCommand() {
        PredicateCollection<Order> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(CLIENT_NAME_CAROL_DAVID_PREDICATE);
        predicateCollection.addPredicate(CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE);
        predicateCollection.addPredicate(CLIENT_PHONE_9110_3216_PREDICATE);
        FindOrderCommand expectedFindOrderCommand = new FindOrderCommand(predicateCollection);

        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Carol David "
                + PREFIX_CLIENT_ADDRESS + "Eunos Bishan "
                + PREFIX_CLIENT_PHONE + "9110 3216 ", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_CLIENT_NAME + "Carol \n \t David "
                + PREFIX_CLIENT_ADDRESS + "Eunos  \n \t Bishan "
                + PREFIX_CLIENT_PHONE + "9110 \n \t 3216 ", expectedFindOrderCommand);
    }
}
