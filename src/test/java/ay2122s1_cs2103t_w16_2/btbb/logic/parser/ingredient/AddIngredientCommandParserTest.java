package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INGREDIENT_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;

public class AddIngredientCommandParserTest {
    private AddIngredientCommandParser parser = new AddIngredientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        IngredientDescriptor expectedIngredientDescriptor = new IngredientDescriptorBuilder(BEEF).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddIngredientCommand(expectedIngredientDescriptor));

        // multiple ingredient names - last ingredient name accepted
        assertParseSuccess(parser, INGREDIENT_NAME_DESC_APPLE + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddIngredientCommand(expectedIngredientDescriptor));

        // multiple quantities - last quantity accepted
        assertParseSuccess(parser, INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_APPLE + QUANTITY_DESC_BEEF
                + UNIT_DESC_BEEF, new AddIngredientCommand(expectedIngredientDescriptor));

        // multiple units - last unit accepted
        assertParseSuccess(parser, INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_APPLE
                + UNIT_DESC_BEEF, new AddIngredientCommand(expectedIngredientDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE);

        // missing ingredient name prefix
        assertParseFailure(parser, VALID_INGREDIENT_NAME_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, INGREDIENT_NAME_DESC_BEEF + VALID_QUANTITY_BEEF + UNIT_DESC_BEEF,
                expectedMessage);

        // missing unit prefix
        assertParseFailure(parser, INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + VALID_UNIT_BEEF,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_INGREDIENT_NAME_BEEF + VALID_QUANTITY_BEEF + VALID_UNIT_BEEF,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ingredient name
        assertParseFailure(parser, INVALID_INGREDIENT_NAME_DESC + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                GenericString.MESSAGE_CONSTRAINTS);

        // invalid quantity
        assertParseFailure(parser, INGREDIENT_NAME_DESC_BEEF + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                Quantity.MESSAGE_CONSTRAINTS);

        // invalid unit
        assertParseFailure(parser, INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + INVALID_UNIT_DESC,
                GenericString.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_INGREDIENT_NAME_DESC + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                GenericString.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
    }
}
