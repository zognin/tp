package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_YES;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BREAD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

        OrderDescriptor editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientIndex(INDEX_SECOND).build();
        // different client index -> returns false
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client name -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY).withClientName(VALID_NAME_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client phone -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client address -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe index -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeIndex(INDEX_SECOND).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe name -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeName("random").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe ingredients -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeIngredients(List.of(AVOCADO, BREAD)).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different order price -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withOrderPrice("10000").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different deadline -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withDeadline("12-12-1998 1830").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different quantity -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withQuantity("10000").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different done status -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));
    }
}
