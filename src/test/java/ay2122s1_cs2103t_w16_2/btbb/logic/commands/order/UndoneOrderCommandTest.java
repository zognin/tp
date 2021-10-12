package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

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
import ay2122s1_cs2103t_w16_2.btbb.model.order.IsDone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class UndoneOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        UndoneOrderCommand undoneFirstCommand = new UndoneOrderCommand(INDEX_FIRST);
        UndoneOrderCommand undoneSecondCommand = new UndoneOrderCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(undoneFirstCommand.equals(undoneFirstCommand));

        // same values -> returns true
        UndoneOrderCommand undoneFirstCommandCopy = new UndoneOrderCommand(INDEX_FIRST);
        assertTrue(undoneFirstCommand.equals(undoneFirstCommandCopy));

        // different types -> returns false
        assertFalse(undoneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(undoneFirstCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(undoneFirstCommand.equals(undoneSecondCommand));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws NotFoundException {
        Order orderToMarkUndone = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order expectedMarkedOrder = new Order(orderToMarkUndone.getClientName(), orderToMarkUndone.getClientPhone(),
                orderToMarkUndone.getClientAddress(), orderToMarkUndone.getRecipeName(),
                orderToMarkUndone.getRecipeIngredients(), orderToMarkUndone.getPrice(),
                orderToMarkUndone.getDeadline(), orderToMarkUndone.getQuantity(), new IsDone(false));
        UndoneOrderCommand undoneOrderCommand = new UndoneOrderCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(UndoneOrderCommand.MESSAGE_UNDONE_ORDER_SUCCESS,
                        orderToMarkUndone.toStringWithoutIsDone());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setOrder(orderToMarkUndone, expectedMarkedOrder);

        assertCommandSuccessWithTabChange(undoneOrderCommand, model, expectedMessage, expectedModel,
                UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        UndoneOrderCommand undoneOrderCommand = new UndoneOrderCommand(outOfBoundIndex);

        assertCommandFailure(undoneOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws NotFoundException {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order markedOrder = new OrderBuilder(orderInFilteredList).withIsDone(new IsDone(false)).build();
        UndoneOrderCommand undoneOrderCommand = new UndoneOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(UndoneOrderCommand.MESSAGE_UNDONE_ORDER_SUCCESS,
                orderInFilteredList.toStringWithoutIsDone());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), markedOrder);

        // Need to update as setOrder method does not update the filtered list of model,
        // but it does update for expectedModel
        model.updateFilteredOrderList(Model.PREDICATE_SHOW_ALL_ORDERS);

        assertCommandSuccessWithTabChange(undoneOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        UndoneOrderCommand undoneOrderCommand = new UndoneOrderCommand(outOfBoundIndex);

        assertCommandFailure(undoneOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
