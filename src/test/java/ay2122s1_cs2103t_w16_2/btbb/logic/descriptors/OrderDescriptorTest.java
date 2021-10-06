package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;

class OrderDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        OrderDescriptor descriptorWithSameValues = new OrderDescriptor(DESC_ORDER_AMY);
        assertTrue(DESC_ORDER_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ORDER_AMY.equals(DESC_ORDER_AMY));

        // null -> returns false
        assertFalse(DESC_ORDER_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_ORDER_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_ORDER_AMY.equals(DESC_ORDER_BOB));

        // different phone -> returns false
        OrderDescriptor editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));
    }
}
