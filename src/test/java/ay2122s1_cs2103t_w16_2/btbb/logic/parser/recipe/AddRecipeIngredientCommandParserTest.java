package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INDEX_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INGREDIENT_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;

public class AddRecipeIngredientCommandParserTest {
    private AddRecipeIngredientCommandParser parser = new AddRecipeIngredientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST;
        IngredientDescriptor expectedIngredientDescriptor = new IngredientDescriptorBuilder(BEEF).build();
        String targetIndexDesc = targetIndex.getOneBased() + " ";

        // whitespace only preamble
        assertParseSuccess(parser, targetIndexDesc + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddRecipeIngredientCommand(targetIndex, expectedIngredientDescriptor));

        // multiple ingredient names - last ingredient name accepted
        assertParseSuccess(parser, targetIndexDesc
                + INGREDIENT_NAME_DESC_APPLE + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddRecipeIngredientCommand(targetIndex, expectedIngredientDescriptor));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, targetIndexDesc
                + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_APPLE + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddRecipeIngredientCommand(targetIndex, expectedIngredientDescriptor));

        // multiple units - last unit accepted
        assertParseSuccess(parser, targetIndexDesc
                + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_APPLE
                + UNIT_DESC_BEEF, new AddRecipeIngredientCommand(targetIndex, expectedIngredientDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String targetIndexDesc = INDEX_FIRST.getOneBased() + " ";
        String expectedMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeIngredientCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, VALID_INGREDIENT_NAME_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                expectedMessage);

        // missing ingredient name prefix
        assertParseFailure(parser, targetIndexDesc + VALID_INGREDIENT_NAME_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, targetIndexDesc + INGREDIENT_NAME_DESC_BEEF + VALID_QUANTITY_BEEF + UNIT_DESC_BEEF,
                expectedMessage);

        // missing unit prefix
        assertParseFailure(parser, targetIndexDesc + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + VALID_UNIT_BEEF,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, targetIndexDesc + VALID_INGREDIENT_NAME_BEEF + VALID_QUANTITY_BEEF + VALID_UNIT_BEEF,
                expectedMessage);

        // all arguments missing
        assertParseFailure(parser, VALID_INGREDIENT_NAME_BEEF + VALID_QUANTITY_BEEF + VALID_UNIT_BEEF,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String targetIndexDesc = INDEX_FIRST.getOneBased() + " ";

        // invalid ingredient index
        assertParseFailure(parser, INVALID_INDEX_DESC
                        + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeIngredientCommand.MESSAGE_USAGE));

        // invalid ingredient name
        assertParseFailure(parser, targetIndexDesc + INVALID_INGREDIENT_NAME_DESC + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                GenericString.getMessageConstraints("Name"));

        // invalid quantity
        assertParseFailure(parser, targetIndexDesc + INGREDIENT_NAME_DESC_BEEF + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                Quantity.MESSAGE_CONSTRAINTS);

        // invalid unit
        assertParseFailure(parser, targetIndexDesc + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + INVALID_UNIT_DESC,
                GenericString.getMessageConstraints("Unit"));


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, targetIndexDesc
                        + INVALID_INGREDIENT_NAME_DESC + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                GenericString.getMessageConstraints("Name"));
    }
}
