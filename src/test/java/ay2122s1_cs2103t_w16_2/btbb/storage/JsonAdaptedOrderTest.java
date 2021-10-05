package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

public class JsonAdaptedOrderTest {
    private static final String INVALID_PHONE = "+651234";

    @Test
    public void toModelType_validOrderDetails_returnsOrder() throws IllegalValueException {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ORDER_BY_BENSON);
        assertEquals(ORDER_BY_BENSON, order.toModelType());
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedOrder order = new JsonAdaptedOrder(INVALID_PHONE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        // Type cast here to avoid conflict with the other JsonAdaptedOrder constructor
        JsonAdaptedOrder order = new JsonAdaptedOrder((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}
