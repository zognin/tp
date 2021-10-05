package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_BY_ELLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(ORDER_BY_ALICE.equals(ORDER_BY_ALICE));

        // null -> returns false
        assertFalse(ORDER_BY_BENSON.equals(null));

        // different phone -> returns false
        Order editedRandomOrder = new OrderBuilder(ORDER_BY_CARL).withPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_BY_CARL.equals(editedRandomOrder));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order randomOrderCopy = new OrderBuilder(ORDER_BY_DANIEL).build();
        assertTrue(ORDER_BY_DANIEL.equals(randomOrderCopy));

        // same object -> returns true
        assertTrue(ORDER_BY_ALICE.equals(ORDER_BY_ALICE));

        // null -> returns false
        assertFalse(ORDER_BY_ALICE.equals(null));

        // different type -> returns false
        assertFalse(ORDER_BY_ALICE.equals(5));

        // different order -> returns false
        assertFalse(ORDER_BY_ALICE.equals(ORDER_BY_ELLE));

        // different phone -> returns false
        Order editedRandomOrder = new OrderBuilder(ORDER_BY_CARL).withPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_BY_CARL.equals(editedRandomOrder));
    }
}
