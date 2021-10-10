package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.CHICKEN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.RICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class JsonAdaptedOrderTest {
    private static final String INVALID_CLIENT_NAME = "R@chel";
    private static final String INVALID_CLIENT_PHONE = "+651234";
    private static final String INVALID_CLIENT_ADDRESS = " ";
    private static final String INVALID_RECIPE_NAME = "Chicken@Rice";
    private static final String INVALID_ORDER_PRICE = "$1.50";
    private static final String INVALID_ORDER_DEADLINE = "2021-12-12 1900";
    private static final String INVALID_ORDER_QUANTITY = "-1";

    private static final String VALID_CLIENT_NAME = BENSON.getName().toString();
    private static final String VALID_CLIENT_PHONE = BENSON.getPhone().toString();
    private static final String VALID_CLIENT_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_RECIPE_NAME = "Chicken Rice";
    private static final List<JsonAdaptedIngredient> VALID_RECIPE_INGREDIENT_LIST = List.of(
            new JsonAdaptedIngredient(
                    CHICKEN.getName().toString(), new Quantity("1").toString(), CHICKEN.getUnit().toString()),
            new JsonAdaptedIngredient(
                    RICE.getName().toString(), new Quantity("100").toString(), RICE.getUnit().toString()));
    private static final String VALID_ORDER_PRICE = "4.50";
    private static final String VALID_ORDER_DEADLINE = "20-12-2021 1200";
    private static final String VALID_ORDER_QUANTITY = "2";

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws IllegalValueException {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER_FOR_BENSON);
        assertEquals(ORDER_FOR_BENSON, order.toModelType());
    }

    @Test
    public void toModelType_validOrderDetailsWithEmptyIngredients_returnsOrder() throws IllegalValueException {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER_FOR_CARL);
        assertEquals(ORDER_FOR_CARL, order.toModelType());
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, INVALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, null, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(INVALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                        VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                        VALID_ORDER_QUANTITY);
        String expectedMessage = GenericString.getMessageConstraints("Name");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, GenericString.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, INVALID_CLIENT_ADDRESS,
                        VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                        VALID_ORDER_QUANTITY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, null,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_invalidRecipeName_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                        INVALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                        VALID_ORDER_QUANTITY);
        String expectedMessage = GenericString.getMessageConstraints("Recipe Name");
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_nullRecipeName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                null, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT,
                        GenericString.getMessageConstraints("Recipe Name"));
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                        VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, INVALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                        VALID_ORDER_QUANTITY);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, null, VALID_ORDER_DEADLINE,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                        VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, INVALID_ORDER_DEADLINE,
                        VALID_ORDER_QUANTITY);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, null,
                VALID_ORDER_QUANTITY);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                        VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                        INVALID_ORDER_QUANTITY);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS,
                VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, VALID_ORDER_PRICE, VALID_ORDER_DEADLINE,
                null);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
