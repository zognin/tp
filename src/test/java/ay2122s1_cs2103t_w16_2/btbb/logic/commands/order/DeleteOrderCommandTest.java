package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showOrderAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingOrderAdded;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteOrderCommand}.
 */
public class DeleteOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteCompletedOrderSameIngredientQuantity_success() throws CommandException {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        modelStub.addOrder(ORDER_FOR_ALICE);
        Ingredient chicken = new Ingredient(new GenericString("Chicken"), new Quantity("100"), new GenericString(
                "whole"));
        modelStub.addIngredient(chicken);

        CommandResult commandResult = new DeleteOrderCommand(INDEX_FIRST).execute(modelStub);

        assertEquals(String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, ORDER_FOR_ALICE),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(chicken), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_deleteIncompleteOrderIngredientQuantityIncreases_success() throws CommandException {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        modelStub.addOrder(ORDER_FOR_BENSON);
        Ingredient fish = new Ingredient(new GenericString("Fish"), new Quantity("100"), new GenericString(
                "whole"));
        modelStub.addIngredient(fish);

        CommandResult commandResult = new DeleteOrderCommand(INDEX_FIRST).execute(modelStub);

        // Order for Benson is incomplete and uses 1 fish for each order. Quantity of fish in inventory has increased
        // by 2.
        Ingredient expectedFish = new Ingredient(
                fish.getName(),
                fish.getQuantity().addQuantityBy(new Quantity("1"), ORDER_FOR_BENSON.getQuantity()),
                fish.getUnit()
        );

        assertEquals(String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, ORDER_FOR_BENSON),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(expectedFish), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws NotFoundException {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteOrder(orderToDelete);

        assertCommandSuccessWithTabChange(deleteOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws NotFoundException {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteOrder(orderToDelete);
        showNoOrder(expectedModel);

        assertCommandSuccessWithTabChange(deleteOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteOrderCommand deleteFirstCommand = new DeleteOrderCommand(INDEX_FIRST);
        DeleteOrderCommand deleteSecondCommand = new DeleteOrderCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteOrderCommand deleteFirstCommandCopy = new DeleteOrderCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different order -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no orders.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredOrderList(p -> false);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
}
