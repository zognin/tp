package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

public class JsonAdaptedOrderTest {
    private static final String INVALID_CLIENT_NAME = "R@chel";
    private static final String INVALID_CLIENT_PHONE = "+651234";
    private static final String INVALID_CLIENT_ADDRESS = " ";

    private static final String VALID_CLIENT_NAME = BENSON.getName().toString();
    private static final String VALID_CLIENT_PHONE = BENSON.getPhone().toString();
    private static final String VALID_CLIENT_ADDRESS = BENSON.getAddress().toString();

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws IllegalValueException {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER_FOR_BENSON);
        assertEquals(ORDER_FOR_BENSON, order.toModelType());
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, INVALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, null, VALID_CLIENT_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(INVALID_CLIENT_NAME, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS);
        String expectedMessage = GenericString.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(null, VALID_CLIENT_PHONE, VALID_CLIENT_ADDRESS);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, GenericString.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, INVALID_CLIENT_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(VALID_CLIENT_NAME, VALID_CLIENT_PHONE, null);
        String expectedMessage = String
                .format(JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
