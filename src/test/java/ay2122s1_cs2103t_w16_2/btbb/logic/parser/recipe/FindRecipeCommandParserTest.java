package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil.MESSAGE_INVALID_KEYWORD;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.RECIPE_NAME_LAKSA_PRATA_PREDICATE;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.FindRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

public class FindRecipeCommandParserTest {
    private FindRecipeCommandParser parser = new FindRecipeCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindRecipeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKeywords_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_RECIPE_NAME + " ", MESSAGE_INVALID_KEYWORD);
    }

    @Test
    public void parse_validArgs_returnsFindRecipeCommand() {
        PredicateCollection<Recipe> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(RECIPE_NAME_LAKSA_PRATA_PREDICATE);
        FindRecipeCommand expectedFindRecipeCommand = new FindRecipeCommand(predicateCollection);

        assertParseSuccess(parser, " " + PREFIX_RECIPE_NAME + "Laksa Prata ", expectedFindRecipeCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_RECIPE_NAME + "Laksa \n \t Prata ", expectedFindRecipeCommand);
    }
}
