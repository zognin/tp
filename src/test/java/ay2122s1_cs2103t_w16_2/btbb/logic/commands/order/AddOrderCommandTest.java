package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingOrderAdded;

public class AddOrderCommandTest {
    @Test
    public void constructor_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOrderCommand(null));
    }

    @Test
    public void execute_orderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        Order validOrder = new OrderBuilder().build();
        OrderDescriptor validOrderDescriptor = new OrderDescriptorBuilder(validOrder).build();

        CommandResult commandResult = new AddOrderCommand(validOrderDescriptor).execute(modelStub);

        assertEquals(String.format(AddOrderCommand.MESSAGE_SUCCESS, validOrder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOrder), modelStub.getOrdersAdded());
    }

    @Test
    public void equals() {
        OrderDescriptor orderDescriptorForAmy = new OrderDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        OrderDescriptor orderDescriptorForBob = new OrderDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        AddOrderCommand addOrderForAmyCommand = new AddOrderCommand(orderDescriptorForAmy);
        AddOrderCommand addOrderForBobCommand = new AddOrderCommand(orderDescriptorForBob);

        // same object -> returns true
        assertTrue(addOrderForAmyCommand.equals(addOrderForAmyCommand));

        // same values -> returns true
        AddOrderCommand addOrderForAmyCommandCopy = new AddOrderCommand(orderDescriptorForAmy);
        assertTrue(addOrderForAmyCommand.equals(addOrderForAmyCommandCopy));

        // different types -> returns false
        assertFalse(addOrderForAmyCommand.equals(1));

        // null -> returns false
        assertFalse(addOrderForAmyCommand.equals(null));

        // different order -> returns false
        assertFalse(addOrderForAmyCommand.equals(addOrderForBobCommand));
    }
}
