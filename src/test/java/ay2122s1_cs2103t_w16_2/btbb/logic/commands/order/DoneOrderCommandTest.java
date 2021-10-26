package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_YES;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showOrderAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class DoneOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DoneOrderCommand doneFirstCommand = new DoneOrderCommand(INDEX_FIRST);
        DoneOrderCommand doneSecondCommand = new DoneOrderCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneOrderCommand doneFirstCommandCopy = new DoneOrderCommand(INDEX_FIRST);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws NotFoundException {
        Order orderToMarkDone = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order expectedMarkedOrder = new Order(orderToMarkDone.getClientName(), orderToMarkDone.getClientPhone(),
                orderToMarkDone.getClientAddress(), orderToMarkDone.getRecipeName(),
                orderToMarkDone.getRecipeIngredients(), orderToMarkDone.getOrderPrice(),
                orderToMarkDone.getDeadline(), orderToMarkDone.getQuantity(), new CompletionStatus(true));
        DoneOrderCommand doneOrderCommand = new DoneOrderCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DoneOrderCommand.MESSAGE_DONE_ORDER_SUCCESS,
                        orderToMarkDone.toStringWithoutCompletionStatus());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setOrder(orderToMarkDone, expectedMarkedOrder);

        assertCommandSuccessWithTabChange(doneOrderCommand, model, expectedMessage, expectedModel,
                UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        DoneOrderCommand doneOrderCommand = new DoneOrderCommand(outOfBoundIndex);

        assertCommandFailure(doneOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws NotFoundException {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order markedOrder = new OrderBuilder(orderInFilteredList)
                .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES).build();
        DoneOrderCommand doneOrderCommand = new DoneOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(DoneOrderCommand.MESSAGE_DONE_ORDER_SUCCESS,
                orderInFilteredList.toStringWithoutCompletionStatus());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), markedOrder);

        // Need to update as setOrder method does not update the filtered list of model,
        // but it does update for expectedModel
        model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);

        assertCommandSuccessWithTabChange(doneOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        DoneOrderCommand doneOrderCommand = new DoneOrderCommand(outOfBoundIndex);

        assertCommandFailure(doneOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no orders.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
