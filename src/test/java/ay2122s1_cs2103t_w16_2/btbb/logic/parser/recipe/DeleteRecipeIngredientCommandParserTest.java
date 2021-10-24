package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_INDEX_DESC_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INGREDIENT_INDEX_DESC_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil.MESSAGE_INVALID_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.DeleteRecipeIngredientCommand;

public class DeleteRecipeIngredientCommandParserTest {
    private DeleteRecipeIngredientCommandParser parser = new DeleteRecipeIngredientCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteRecipeIngredientCommand() {
        assertParseSuccess(parser, "1 " + INGREDIENT_INDEX_DESC_FIRST,
                new DeleteRecipeIngredientCommand(INDEX_FIRST, INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid recipe index
        assertParseFailure(parser, "a " + INGREDIENT_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRecipeIngredientCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-100" + INGREDIENT_INDEX_DESC_FIRST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRecipeIngredientCommand.MESSAGE_USAGE));

        // Invalid ingredient index
        assertParseFailure(parser, "1 " + INVALID_INGREDIENT_INDEX_DESC_FIRST, MESSAGE_INVALID_INDEX);

        // Both args invalid -> invalid ingredient index message shown
        assertParseFailure(parser, "-1 " + INVALID_INGREDIENT_INDEX_DESC_FIRST, MESSAGE_INVALID_INDEX);
    }
}
