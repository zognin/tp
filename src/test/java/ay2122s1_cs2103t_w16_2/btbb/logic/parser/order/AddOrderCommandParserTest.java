package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_IMRAN;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DEADLINE_DESC_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DEADLINE_DESC_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INDEX_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INDEX_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_INDEX_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ORDER_PRICE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ORDER_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_INGREDIENT_LIST_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_RECIPE_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_IMRAN;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_PRICE_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_PRICE_DESC_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ORDER_QUANTITY_DESC_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_IMRAN;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_INDEX_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_INGREDIENT_LIST_DESC_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_INGREDIENT_LIST_DESC_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.RECIPE_NAME_DESC_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_IMRAN;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;

class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        OrderDescriptor expectedOrderDescriptorWithoutClientIndex =
                new OrderDescriptorBuilder(ORDER_FOR_BOB).build();

        OrderDescriptor expectedOrderDescriptorWithoutClientIndexAndIngredientList =
                new OrderDescriptorBuilder(ORDER_FOR_AMY).withRecipeIngredients(new ArrayList<>()).build();

        OrderDescriptor expectedOrderDescriptorWithoutClientIndexAndOrderQuantity =
                new OrderDescriptorBuilder(ORDER_FOR_IMRAN).withQuantity(VALID_ORDER_QUANTITY_1).build();

        OrderDescriptor expectedOrderDescriptorWithClientIndexAndParams =
                new OrderDescriptorBuilder(ORDER_FOR_BOB).withClientIndex(INDEX_FIRST).build();

        OrderDescriptor expectedOrderDescriptorWithRecipeIndexAndParams =
                new OrderDescriptorBuilder(ORDER_FOR_BOB).withRecipeIndex(INDEX_FIRST).build();

        OrderDescriptor expectedOrderDescriptorWithClientIndexAndRecipeIndex =
                new OrderDescriptorBuilder(ORDER_FOR_BOB)
                        .withClientIndex(INDEX_FIRST)
                        .withRecipeIndex(INDEX_FIRST)
                        .build();

        //=========== Without Client Index ============================================================================

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PHONE_DESC_BOB + NAME_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple names without client index - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple phones without client index - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple addresses without client index - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple recipe name without client index - last recipe name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_CHICKEN_RICE + RECIPE_NAME_DESC_LAKSA
                        + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple recipe ingredient list without client index - last list accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_1
                        + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple order price without client index - last order price accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_1
                        + ORDER_PRICE_DESC_2 + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple order deadline without client index - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_1
                        + ORDER_PRICE_DESC_2 + DEADLINE_DESC_DECEMBER + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        // multiple order quantity without client index - last quantity accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_1
                        + ORDER_PRICE_DESC_2 + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_1 + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndex));

        //=========== Without Client Index and ingredient list ========================================================

        assertParseSuccess(parser, PHONE_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
                        + RECIPE_NAME_DESC_CHICKEN_RICE + ORDER_PRICE_DESC_1 + DEADLINE_DESC_DECEMBER
                        + ORDER_QUANTITY_DESC_1,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndexAndIngredientList));

        //=========== Without Client Index and order quantity ========================================================

        assertParseSuccess(parser, PHONE_DESC_IMRAN + NAME_DESC_IMRAN + ADDRESS_DESC_IMRAN
                        + RECIPE_NAME_DESC_CHICKEN_RICE + RECIPE_INGREDIENT_LIST_DESC_1 + ORDER_PRICE_DESC_1
                        + DEADLINE_DESC_DECEMBER,
                new AddOrderCommand(expectedOrderDescriptorWithoutClientIndexAndOrderQuantity));

        //=========== With Client Index and without Recipe Index =====================================================

        // multiple client index with client details - last index accepted
        assertParseSuccess(parser,
                INDEX_DESC_AMY + INDEX_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithClientIndexAndParams));

        // ========== With Recipe Index ==============================================================================

        // recipe index with recipe details
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + RECIPE_INDEX_DESC_1
                + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithRecipeIndexAndParams));

        // ========== With Recipe and Client Index ===================================================================

        assertParseSuccess(parser,
                INDEX_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB + RECIPE_INDEX_DESC_1
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                new AddOrderCommand(expectedOrderDescriptorWithClientIndexAndRecipeIndex));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing name
        assertParseFailure(parser, PHONE_DESC_BOB + ADDRESS_DESC_BOB + RECIPE_NAME_DESC_LAKSA
                        + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                expectedMessage);

        // missing phone
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + RECIPE_NAME_DESC_LAKSA
                        + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                expectedMessage);

        // missing address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                expectedMessage);

        // missing recipe name
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                expectedMessage);


        // missing price
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                expectedMessage);

        // missing deadline
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + ORDER_QUANTITY_DESC_2,
                expectedMessage);

        // all compulsory fields missing
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, INVALID_INDEX_DESC + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2
                        + ORDER_PRICE_DESC_2 + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                GenericString.getMessageConstraints("Name"));

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + INVALID_PHONE_DESC
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ADDRESS_DESC + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                Address.MESSAGE_CONSTRAINTS);

        // invalid recipe name
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + INVALID_RECIPE_NAME_DESC + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                GenericString.getMessageConstraints("Recipe Name"));

        // invalid recipe ingredients
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + INVALID_RECIPE_INGREDIENT_LIST_DESC + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                RecipeIngredientList.MESSAGE_CONSTRAINTS);

        // invalid order price
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + INVALID_ORDER_PRICE_DESC
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_2,
                OrderPrice.MESSAGE_CONSTRAINTS);

        // invalid order deadline
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + INVALID_DEADLINE_DESC + ORDER_QUANTITY_DESC_2,
                Deadline.MESSAGE_CONSTRAINTS);

        // invalid order quantity
        assertParseFailure(parser, NAME_DESC_BOB + ADDRESS_DESC_BOB + PHONE_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + INVALID_ORDER_QUANTITY_DESC,
                Quantity.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PHONE_DESC_BOB + NAME_DESC_BOB + ADDRESS_DESC_BOB
                        + RECIPE_NAME_DESC_LAKSA + RECIPE_INGREDIENT_LIST_DESC_2 + ORDER_PRICE_DESC_2
                        + DEADLINE_DESC_MARCH + ORDER_QUANTITY_DESC_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));
    }
}
