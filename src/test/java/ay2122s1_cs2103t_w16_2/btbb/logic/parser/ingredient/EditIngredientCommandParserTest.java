package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_NOT_EDITED;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INGREDIENT_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_UNIT_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.EditIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;

public class EditIngredientCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIngredientCommand.MESSAGE_USAGE);

    private EditIngredientCommandParser parser = new EditIngredientCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_INGREDIENT_NAME_BEEF, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + INGREDIENT_NAME_DESC_BEEF, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + INGREDIENT_NAME_DESC_BEEF, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_INGREDIENT_NAME_DESC,
                GenericString.getMessageConstraints("Name")); // invalid name
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_INTERNAL_CONSTRAINTS); // invalid quantity
        assertParseFailure(parser, "1" + INVALID_UNIT_DESC,
                GenericString.getMessageConstraints("Unit")); // invalid unit

        // invalid quantity followed by valid unit
        assertParseFailure(parser, "1" + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                Quantity.MESSAGE_INTERNAL_CONSTRAINTS);

        // valid quantity followed by invalid quantity. The test case for invalid quantity followed by valid quantity
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + QUANTITY_DESC_BEEF + INVALID_QUANTITY_DESC,
                Quantity.MESSAGE_INTERNAL_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_INGREDIENT_NAME_DESC + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF,
                GenericString.getMessageConstraints("Name"));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF;

        IngredientDescriptor descriptor = new IngredientDescriptorBuilder()
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).withQuantity(VALID_QUANTITY_BEEF)
                .withUnit(VALID_UNIT_BEEF).build();
        EditIngredientCommand expectedCommand = new EditIngredientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF;

        IngredientDescriptor descriptor = new IngredientDescriptorBuilder().withQuantity(VALID_QUANTITY_BEEF)
                .withUnit(VALID_UNIT_BEEF).build();
        EditIngredientCommand expectedCommand = new EditIngredientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + INGREDIENT_NAME_DESC_BEEF;
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder()
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        EditIngredientCommand expectedCommand = new EditIngredientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = targetIndex.getOneBased() + QUANTITY_DESC_BEEF;
        descriptor = new IngredientDescriptorBuilder().withQuantity(VALID_QUANTITY_BEEF).build();
        expectedCommand = new EditIngredientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit
        userInput = targetIndex.getOneBased() + UNIT_DESC_BEEF;
        descriptor = new IngredientDescriptorBuilder().withUnit(VALID_UNIT_BEEF).build();
        expectedCommand = new EditIngredientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF
                + INGREDIENT_NAME_DESC_APPLE + QUANTITY_DESC_APPLE + UNIT_DESC_APPLE;

        IngredientDescriptor descriptor = new IngredientDescriptorBuilder()
                .withIngredientName(VALID_INGREDIENT_NAME_APPLE).withQuantity(VALID_QUANTITY_APPLE)
                .withUnit(VALID_UNIT_APPLE).build();
        EditIngredientCommand expectedCommand = new EditIngredientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_QUANTITY_DESC + QUANTITY_DESC_BEEF;
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder().withQuantity(VALID_QUANTITY_BEEF).build();
        EditIngredientCommand expectedCommand = new EditIngredientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INGREDIENT_NAME_DESC_BEEF + INVALID_QUANTITY_DESC + UNIT_DESC_BEEF
            + QUANTITY_DESC_BEEF;
        descriptor = new IngredientDescriptorBuilder().withIngredientName(VALID_INGREDIENT_NAME_BEEF)
                .withQuantity(VALID_QUANTITY_BEEF).withUnit(VALID_UNIT_BEEF).build();
        expectedCommand = new EditIngredientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
