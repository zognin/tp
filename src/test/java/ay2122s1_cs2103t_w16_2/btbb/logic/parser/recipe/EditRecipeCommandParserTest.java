package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_NOT_EDITED;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_PRICE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_PRICE_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_APPLE_PIE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_PRICE_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.EditRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipePrice;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;

public class EditRecipeCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRecipeCommand.MESSAGE_USAGE);

    private EditRecipeCommandParser parser = new EditRecipeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_RECIPE_NAME_APPLE_PIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + RECIPE_NAME_DESC_LAKSA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + RECIPE_NAME_DESC_LAKSA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_RECIPE_NAME_DESC,
                GenericString.getMessageConstraints("Recipe Name"));
        assertParseFailure(parser, "1" + INVALID_RECIPE_PRICE_DESC, RecipePrice.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid price
        assertParseFailure(parser, "1" + INVALID_RECIPE_NAME_DESC + RECIPE_PRICE_DESC_LAKSA,
                GenericString.getMessageConstraints("Recipe Name"));

        // valid name followed by invalid name.
        assertParseFailure(parser, "1" + RECIPE_NAME_DESC_LAKSA + INVALID_RECIPE_NAME_DESC
                + RECIPE_PRICE_DESC_LAKSA, GenericString.getMessageConstraints("Recipe Name"));

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_RECIPE_NAME_DESC + INVALID_RECIPE_PRICE_DESC,
                GenericString.getMessageConstraints("Recipe Name"));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + RECIPE_NAME_DESC_LAKSA + RECIPE_PRICE_DESC_LAKSA;

        RecipeDescriptor descriptor = new RecipeDescriptorBuilder().withName(VALID_RECIPE_NAME_LAKSA)
                .withRecipePrice(VALID_RECIPE_PRICE_LAKSA).build();
        EditRecipeCommand expectedCommand = new EditRecipeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + RECIPE_NAME_DESC_LAKSA;

        RecipeDescriptor descriptor = new RecipeDescriptorBuilder().withName(VALID_RECIPE_NAME_LAKSA).build();
        EditRecipeCommand expectedCommand = new EditRecipeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
