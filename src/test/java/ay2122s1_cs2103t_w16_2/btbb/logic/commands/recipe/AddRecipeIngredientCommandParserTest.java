package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_RECIPE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeUtil;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddRecipeIngredientCommand.
 */
public class AddRecipeIngredientCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddRecipeIngredientCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new AddRecipeIngredientCommand(INDEX_FIRST, null));
        assertThrows(NullPointerException.class, () ->
                new AddRecipeIngredientCommand(null, new IngredientDescriptor()));
    }

    @Test
    public void execute_validIngredientAndRecipeIndex_addSuccessful() throws Exception {
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddRecipeIngredientCommand command = new AddRecipeIngredientCommand(INDEX_FIRST, validIngredientDescriptor);
        Recipe recipeToEdit = model.getFilteredRecipeList().get(INDEX_FIRST.getZeroBased());
        RecipeIngredientList newRecipeIngredientList =
                RecipeUtil.addIngredientToIngredientList(recipeToEdit, validIngredient);
        Recipe editedRecipe = new RecipeBuilder(recipeToEdit).withRecipeIngredients(newRecipeIngredientList).build();

        String expectedMessage = String.format(
                AddRecipeIngredientCommand.MESSAGE_ADD_RECIPE_INGREDIENT_SUCCESS, validIngredient, editedRecipe);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setRecipe(recipeToEdit, editedRecipe);

        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateIngredient_failure() {
        Recipe recipe = model.getFilteredRecipeList().get(INDEX_FIRST.getZeroBased());
        Ingredient duplicateIngredient = recipe.getRecipeIngredients().getIngredients().get(INDEX_FIRST.getZeroBased());
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(duplicateIngredient).build();
        AddRecipeIngredientCommand command = new AddRecipeIngredientCommand(INDEX_FIRST, validIngredientDescriptor);

        assertCommandFailure(command, model, AddRecipeIngredientCommand.MESSAGE_DUPLICATE_RECIPE_INGREDIENT);
    }

    @Test
    public void execute_duplicateRecipe_failure() {
        Ingredient firstIngredient = new IngredientBuilder().withIngredientName("Random name 1").build();
        Ingredient secondIngredient = new IngredientBuilder().withIngredientName("Random name 2").build();
        IngredientDescriptor secondIngredientDescriptor = new IngredientDescriptorBuilder(secondIngredient).build();
        RecipeIngredientList firstIngredientList = new RecipeIngredientList(List.of(firstIngredient));
        RecipeIngredientList secondIngredientList =
                new RecipeIngredientList(List.of(firstIngredient, secondIngredient));
        Recipe firstRecipe = new RecipeBuilder().withRecipeIngredients(firstIngredientList).build();
        Recipe secondRecipe = new RecipeBuilder().withRecipeIngredients(secondIngredientList).build();
        model.addRecipe(firstRecipe);
        model.addRecipe(secondRecipe);

        Index indexOfRecipeToEdit = Index.fromZeroBased(model.getFilteredRecipeList().indexOf(firstRecipe));

        AddRecipeIngredientCommand command =
                new AddRecipeIngredientCommand(indexOfRecipeToEdit, secondIngredientDescriptor);
        assertCommandFailure(command, model, MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddRecipeIngredientCommand command = new AddRecipeIngredientCommand(outOfBoundIndex, validIngredientDescriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRecipeList().size());

        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddRecipeIngredientCommand command = new AddRecipeIngredientCommand(outOfBoundIndex, validIngredientDescriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddRecipeIngredientCommand standardCommand = new AddRecipeIngredientCommand(INDEX_FIRST, DESC_APPLE);

        // same values -> returns true
        IngredientDescriptor copyDescriptor = new IngredientDescriptor(DESC_APPLE);
        AddRecipeIngredientCommand commandWithSameValues = new AddRecipeIngredientCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddRecipeIngredientCommand(INDEX_SECOND, DESC_APPLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddRecipeIngredientCommand(INDEX_FIRST, DESC_BEEF)));
    }
}
