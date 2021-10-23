package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_INGREDIENT_LIST_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_PRICE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_INGREDIENT_LIST_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_PRICE_DESC_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_PRICE_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_APPLE_PIE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_LAKSA;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipePrice;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;

public class AddRecipeCommandParserTest {
    private AddRecipeCommandParser parser = new AddRecipeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        RecipeDescriptor expectedRecipeDescriptor = new RecipeDescriptorBuilder(RECIPE_LAKSA).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RECIPE_NAME_DESC_LAKSA
                + RECIPE_INGREDIENT_LIST_DESC_LAKSA + RECIPE_PRICE_DESC_LAKSA,
                new AddRecipeCommand(expectedRecipeDescriptor));

        // multiple names - last name accepted
        assertParseSuccess(parser, RECIPE_NAME_DESC_CHICKEN_RICE + RECIPE_NAME_DESC_LAKSA
                + RECIPE_INGREDIENT_LIST_DESC_LAKSA + RECIPE_PRICE_DESC_LAKSA,
                new AddRecipeCommand(expectedRecipeDescriptor));

        // multiple prices - last price accepted
        assertParseSuccess(parser, RECIPE_NAME_DESC_LAKSA
                        + RECIPE_INGREDIENT_LIST_DESC_LAKSA + RECIPE_PRICE_DESC_CHICKEN_RICE + RECIPE_PRICE_DESC_LAKSA,
                new AddRecipeCommand(expectedRecipeDescriptor));

        // ingredients not provided
        expectedRecipeDescriptor.setRecipeIngredients(new RecipeIngredientList(new ArrayList<>()));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + RECIPE_NAME_DESC_LAKSA + RECIPE_PRICE_DESC_LAKSA,
                new AddRecipeCommand(expectedRecipeDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_RECIPE_NAME_APPLE_PIE + RECIPE_PRICE_DESC_CHICKEN_RICE,
                expectedMessage);

        // missing price prefix
        assertParseFailure(parser, RECIPE_NAME_DESC_CHICKEN_RICE + VALID_PRICE_1,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_RECIPE_NAME_APPLE_PIE + VALID_PRICE_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_RECIPE_NAME_DESC + RECIPE_INGREDIENT_LIST_DESC_LAKSA
                        + RECIPE_PRICE_DESC_LAKSA, GenericString.getMessageConstraints("Recipe Name"));

        // invalid recipe ingredients
        assertParseFailure(parser, RECIPE_NAME_DESC_LAKSA + INVALID_RECIPE_INGREDIENT_LIST_DESC
                        + RECIPE_PRICE_DESC_LAKSA, RecipeIngredientList.MESSAGE_CONSTRAINTS);

        // invalid price
        assertParseFailure(parser, RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_LAKSA
                        + INVALID_RECIPE_PRICE_DESC, RecipePrice.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_RECIPE_NAME_DESC + INVALID_RECIPE_INGREDIENT_LIST_DESC
                        + RECIPE_PRICE_DESC_LAKSA, GenericString.getMessageConstraints("Recipe Name"));

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_LAKSA
                        + RECIPE_PRICE_DESC_LAKSA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
    }
}
