package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ELLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(ORDER_FOR_ALICE.equals(ORDER_FOR_ALICE));

        // null -> returns false
        assertFalse(ORDER_FOR_BENSON.equals(null));

        // different phone -> returns false
        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_CARL.equals(editedRandomOrder));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order orderCopy = new OrderBuilder(ORDER_FOR_DANIEL).build();
        assertTrue(ORDER_FOR_DANIEL.equals(orderCopy));

        // same object -> returns true
        assertTrue(ORDER_FOR_ALICE.equals(ORDER_FOR_ALICE));

        // null -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(null));

        // different type -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(5));

        // different order -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(ORDER_FOR_ELLE));

        // different phone -> returns false
        Order editedOrder = new OrderBuilder(ORDER_FOR_CARL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_CARL.equals(editedOrder));
    }
}
