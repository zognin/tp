package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeUtil.removeIngredientFromIngredientList;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;
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
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for DeleteRecipeIngredientCommand.
 */
public class DeleteRecipeIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteRecipeIngredientCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new DeleteRecipeIngredientCommand(INDEX_FIRST, null));
        assertThrows(NullPointerException.class, () ->
                new DeleteRecipeIngredientCommand(null, null));
    }

    @Test
    public void execute_validRecipeIndex_success() throws NotFoundException {
        RecipeIngredientList recipeIngredientList = new RecipeIngredientList(List.of(new IngredientBuilder().build()));
        Recipe recipeToEdit = new RecipeBuilder()
                .withName("Random")
                .withRecipeIngredients(recipeIngredientList).build();
        model.addRecipe(recipeToEdit);
        Index editedRecipeIndex = Index.fromZeroBased(model.getFilteredRecipeList().indexOf(recipeToEdit));
        Ingredient ingredientToDelete = new IngredientBuilder().build();

        DeleteRecipeIngredientCommand command =
                new DeleteRecipeIngredientCommand(INDEX_FIRST, editedRecipeIndex);
        RecipeIngredientList editedIngredientList = removeIngredientFromIngredientList(recipeToEdit,
                ingredientToDelete);
        Recipe editedRecipe = new RecipeBuilder(recipeToEdit).withRecipeIngredients(editedIngredientList).build();

        String expectedMessage = String.format(
                DeleteRecipeIngredientCommand.MESSAGE_DELETE_RECIPE_INGREDIENT_SUCCESS, ingredientToDelete,
                editedRecipe);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRecipe(recipeToEdit, editedRecipe);

        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_invalidRecipeIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRecipeList().size());

        DeleteRecipeIngredientCommand command = new DeleteRecipeIngredientCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        DeleteRecipeIngredientCommand command = new DeleteRecipeIngredientCommand(INDEX_FIRST, outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIngredientIndex_failure() {
        Index targetRecipeIndex = Index.fromZeroBased(model.getFilteredRecipeList().size());
        RecipeIngredientList recipeIngredientList = new RecipeIngredientList(List.of(new IngredientBuilder().build()));
        Recipe recipeToEdit = new RecipeBuilder()
                .withName("Random")
                .withRecipeIngredients(recipeIngredientList).build();
        model.addRecipe(recipeToEdit);
        Index outOfBoundIndex = Index.fromOneBased(recipeIngredientList.getIngredients().size() + 1);

        DeleteRecipeIngredientCommand command = new DeleteRecipeIngredientCommand(outOfBoundIndex, targetRecipeIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteRecipeIngredientCommand standardCommand =
                new DeleteRecipeIngredientCommand(INDEX_FIRST, INDEX_SECOND);

        // same values -> returns true
        DeleteRecipeIngredientCommand commandWithSameValues =
                new DeleteRecipeIngredientCommand(INDEX_FIRST, INDEX_SECOND);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteRecipeIngredientCommand(INDEX_SECOND, INDEX_SECOND)));
        assertFalse(standardCommand.equals(new DeleteRecipeIngredientCommand(INDEX_FIRST, INDEX_FIRST)));
    }
}
