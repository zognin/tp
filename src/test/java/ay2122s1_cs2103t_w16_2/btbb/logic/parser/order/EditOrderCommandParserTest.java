package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DEADLINE_DESC_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DEADLINE_DESC_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ORDER_PRICE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ORDER_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_PRICE_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_PRICE_DESC_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.EditOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;

class EditOrderCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE);

    private EditOrderCommandParser parser = new EditOrderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditOrderCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC,
                GenericString.getMessageConstraints("Name")); // invalid client name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS); // invalid client phone
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC,
                Address.MESSAGE_CONSTRAINTS); // invalid client address
        assertParseFailure(parser, "1" + INVALID_RECIPE_NAME_DESC,
                GenericString.getMessageConstraints("Recipe Name")); // invalid client address
        assertParseFailure(parser, "1" + INVALID_RECIPE_NAME_DESC,
                GenericString.getMessageConstraints("Recipe Name")); // invalid recipe name
        assertParseFailure(parser, "1" + INVALID_ORDER_PRICE_DESC,
                Price.MESSAGE_CONSTRAINTS); // invalid order price
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC,
                Deadline.MESSAGE_CONSTRAINTS); // invalid order deadline
        assertParseFailure(parser, "1" + INVALID_ORDER_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS); // invalid order quantity

        // invalid phone followed by valid address
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + ADDRESS_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_ADDRESS_DESC + VALID_PHONE_AMY,
                GenericString.getMessageConstraints("Name"));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + RECIPE_NAME_DESC_LAKSA + ORDER_PRICE_DESC_1 + DEADLINE_DESC_DECEMBER + ORDER_QUANTITY_DESC_1;

        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientName(VALID_NAME_AMY)
                .withClientPhone(VALID_PHONE_BOB).withClientAddress(VALID_ADDRESS_BOB)
                .withRecipeName(VALID_RECIPE_NAME_LAKSA).withPrice(VALID_PRICE_1)
                .withDeadline(VALID_DEADLINE_DECEMBER).withQuantity(VALID_ORDER_QUANTITY_1).build();
        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + ADDRESS_DESC_BOB;

        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientPhone(VALID_PHONE_BOB)
                .withClientAddress(VALID_ADDRESS_BOB).build();
        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // client name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientName(VALID_NAME_AMY).build();
        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // client phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new OrderDescriptorBuilder().withClientPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // client address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new OrderDescriptorBuilder().withClientAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // recipe name
        userInput = targetIndex.getOneBased() + RECIPE_NAME_DESC_LAKSA;
        descriptor = new OrderDescriptorBuilder().withRecipeName(VALID_RECIPE_NAME_LAKSA).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order price
        userInput = targetIndex.getOneBased() + ORDER_PRICE_DESC_2;
        descriptor = new OrderDescriptorBuilder().withPrice(VALID_ORDER_QUANTITY_2).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_MARCH;
        descriptor = new OrderDescriptorBuilder().withDeadline(VALID_DEADLINE_MARCH).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // order quantity
        userInput = targetIndex.getOneBased() + ORDER_QUANTITY_DESC_1;
        descriptor = new OrderDescriptorBuilder().withQuantity(VALID_ORDER_QUANTITY_1).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB;

        OrderDescriptor descriptor = new OrderDescriptorBuilder()
                .withClientAddress(VALID_ADDRESS_BOB).withClientPhone(VALID_PHONE_BOB).build();
        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientPhone(VALID_PHONE_BOB).build();
        EditOrderCommand expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + RECIPE_NAME_DESC_LAKSA + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new OrderDescriptorBuilder().withClientPhone(VALID_PHONE_BOB)
                .withClientAddress(VALID_ADDRESS_BOB).withRecipeName(VALID_RECIPE_NAME_LAKSA).build();
        expectedCommand = new EditOrderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
