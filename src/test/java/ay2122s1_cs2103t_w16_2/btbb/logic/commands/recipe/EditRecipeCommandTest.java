package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE_PIE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF_STEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_APPLE_PIE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.ListIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class EditRecipeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Recipe editedRecipe = new RecipeBuilder().build();
        RecipeDescriptor descriptor = new RecipeDescriptorBuilder(editedRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccessWithTabChange(editRecipeCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Index indexLastRecipe = Index.fromOneBased(model.getFilteredRecipeList().size());
        Recipe lastRecipe = model.getFilteredRecipeList().get(indexLastRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(lastRecipe);
        Recipe editedRecipe = recipeInList.withName(VALID_RECIPE_NAME_APPLE_PIE)
                .withRecipeIngredients(new RecipeIngredientList(new ArrayList<>()))
                .build();

        RecipeDescriptor descriptor = new RecipeDescriptorBuilder()
                .withName(VALID_RECIPE_NAME_APPLE_PIE)
                .withRecipeIngredients(new ArrayList<>())
                .build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexLastRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccessWithTabChange(editRecipeCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST, new RecipeDescriptor());
        Recipe editedRecipe = model.getFilteredRecipeList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccessWithTabChange(editRecipeCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_filteredList_success() throws NotFoundException {
        showRecipeAtIndex(model, INDEX_FIRST);

        Recipe recipeInFilteredList = model.getFilteredRecipeList().get(INDEX_FIRST.getZeroBased());
        Recipe editedRecipe = new RecipeBuilder(recipeInFilteredList).withName(VALID_RECIPE_NAME_APPLE_PIE).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST,
                new RecipeDescriptorBuilder().withName(VALID_RECIPE_NAME_APPLE_PIE).build());

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccessWithTabChange(editRecipeCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateRecipeUnfilteredList_failure() {
        Recipe firstRecipe = model.getFilteredRecipeList().get(INDEX_FIRST.getZeroBased());
        RecipeDescriptor descriptor = new RecipeDescriptorBuilder(firstRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipeFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST);

        Recipe recipeInList = model.getAddressBook().getRecipeList().get(INDEX_SECOND.getZeroBased());
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST,
                new RecipeDescriptorBuilder(recipeInList).build());

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        RecipeDescriptor descriptor = new RecipeDescriptorBuilder()
                .withName(VALID_RECIPE_NAME_APPLE_PIE).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRecipeList().size());

        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex,
                new RecipeDescriptorBuilder().withName(VALID_RECIPE_NAME_APPLE_PIE).build());

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditRecipeCommand standardCommand = new EditRecipeCommand(INDEX_FIRST, DESC_APPLE_PIE);

        // same values -> returns true
        RecipeDescriptor copyDescriptor = new RecipeDescriptor(DESC_APPLE_PIE);
        EditRecipeCommand commandWithSameValues = new EditRecipeCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListIngredientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_SECOND, DESC_APPLE_PIE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_FIRST, DESC_BEEF_STEW)));
    }
}
