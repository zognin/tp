package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showOrderAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.OrderUtil.removeIngredientFromIngredientList;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteOrderIngredientCommand.
 */
public class DeleteOrderIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteOrderIngredientCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new DeleteOrderIngredientCommand(INDEX_FIRST, null));
        assertThrows(NullPointerException.class, () ->
                new DeleteOrderIngredientCommand(null, null));
    }

    @Test
    public void execute_validOrderIndex_success() throws NotFoundException {
        Index editedOrderIndex = Index.fromZeroBased(model.getFilteredOrderList().size());
        RecipeIngredientList recipeIngredientList = new RecipeIngredientList(List.of(new IngredientBuilder().build()));
        Order orderToEdit = new OrderBuilder()
                .withRecipeName(new GenericString("Random"))
                .withRecipeIngredients(recipeIngredientList).build();
        model.addOrder(orderToEdit);
        Ingredient ingredientToDelete = new IngredientBuilder().build();

        DeleteOrderIngredientCommand command =
                new DeleteOrderIngredientCommand(INDEX_FIRST, editedOrderIndex);
        RecipeIngredientList editedIngredientList = removeIngredientFromIngredientList(orderToEdit, ingredientToDelete);
        Order editedOrder = new OrderBuilder(orderToEdit).withRecipeIngredients(editedIngredientList).build();

        String expectedMessage = String.format(
                DeleteOrderIngredientCommand.MESSAGE_DELETE_ORDER_INGREDIENT_SUCCESS, ingredientToDelete, editedOrder);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(orderToEdit, editedOrder);

        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidOrderIndexFilteredList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        DeleteOrderIngredientCommand command = new DeleteOrderIngredientCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        DeleteOrderIngredientCommand command = new DeleteOrderIngredientCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIngredientIndex_failure() {
        Index targetOrderIndex = Index.fromZeroBased(model.getFilteredOrderList().size());
        RecipeIngredientList recipeIngredientList = new RecipeIngredientList(List.of(new IngredientBuilder().build()));
        Order orderToEdit = new OrderBuilder()
                .withRecipeName(new GenericString("Random"))
                .withRecipeIngredients(recipeIngredientList).build();
        model.addOrder(orderToEdit);
        Index outOfBoundIndex = Index.fromOneBased(recipeIngredientList.getIngredients().size() + 1);

        DeleteOrderIngredientCommand command = new DeleteOrderIngredientCommand(outOfBoundIndex, targetOrderIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteOrderIngredientCommand standardCommand =
                new DeleteOrderIngredientCommand(INDEX_FIRST, INDEX_SECOND);

        // same values -> returns true
        DeleteOrderIngredientCommand commandWithSameValues =
                new DeleteOrderIngredientCommand(INDEX_FIRST, INDEX_SECOND);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteOrderIngredientCommand(INDEX_SECOND, INDEX_SECOND)));
        assertFalse(standardCommand.equals(new DeleteOrderIngredientCommand(INDEX_FIRST, INDEX_FIRST)));
    }
}
