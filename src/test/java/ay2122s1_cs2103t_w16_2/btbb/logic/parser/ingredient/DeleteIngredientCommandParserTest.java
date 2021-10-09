package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.DeleteIngredientCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteIngredientCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteIngredientCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteIngredientCommandParserTest {
    private DeleteIngredientCommandParser parser = new DeleteIngredientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteClientCommand() {
        assertParseSuccess(parser, "1", new DeleteIngredientCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteIngredientCommand.MESSAGE_USAGE));
    }
}
