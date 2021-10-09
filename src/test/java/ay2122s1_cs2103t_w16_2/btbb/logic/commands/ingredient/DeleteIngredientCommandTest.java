package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;


public class DeleteIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DeleteIngredientCommand deleteFirstCommand = new DeleteIngredientCommand(INDEX_FIRST);
        DeleteIngredientCommand deleteSecondCommand = new DeleteIngredientCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteIngredientCommand deleteFirstCommandCopy = new DeleteIngredientCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws NotFoundException {
        Ingredient ingredientToDelete = model.getFilteredIngredientList().get(INDEX_FIRST.getZeroBased());
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteIngredient(ingredientToDelete);

        assertCommandSuccessWithTabChange(deleteIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIngredientList().size() + 1);
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws NotFoundException {
        showIngredientAtIndex(model, INDEX_FIRST);

        Ingredient ingredientToDelete = model.getFilteredIngredientList().get(INDEX_FIRST.getZeroBased());
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteIngredient(ingredientToDelete);
        showNoIngredient(expectedModel);

        assertCommandSuccessWithTabChange(deleteIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showIngredientAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getIngredientList().size());

        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIngredient(Model model) {
        model.updateFilteredIngredientList(p -> false);

        assertTrue(model.getFilteredIngredientList().isEmpty());
    }
}
