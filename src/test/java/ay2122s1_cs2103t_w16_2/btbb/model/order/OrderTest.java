package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ELLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(ORDER_FOR_ALICE.isSameOrder(ORDER_FOR_ALICE));

        // null -> returns false
        assertFalse(ORDER_FOR_BENSON.isSameOrder(null));

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB))
                .withClientPhone(new Phone(VALID_PHONE_BOB))
                .withClientAddress(new Address(VALID_ADDRESS_BOB)).build();
        // different object, same client name, same client phone and same client address -> returns true
        assertTrue(ORDER_FOR_BOB.isSameOrder(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_CARL.isSameOrder(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL)
                .withClientName(new GenericString(VALID_NAME_AMY)).build();
        assertFalse(ORDER_FOR_DANIEL.isSameOrder(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress(new Address("Choa Chu Kang")).build();
        assertFalse(ORDER_FOR_ELLE.isSameOrder(editedRandomOrder));
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

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB))
                .withClientPhone(new Phone(VALID_PHONE_BOB))
                .withClientAddress(new Address(VALID_ADDRESS_BOB)).build();
        // different object, same client name, same client phone and same client address -> returns true
        assertTrue(ORDER_FOR_BOB.equals(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB)).build();
        assertFalse(ORDER_FOR_CARL.equals(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_DANIEL.equals(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress(new Address(VALID_ADDRESS_BOB)).build();
        assertFalse(ORDER_FOR_ELLE.equals(editedRandomOrder));
    }
}
