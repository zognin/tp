package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.testutil.Assert;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStub;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingRecipeAdded;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubWithRecipe;

public class AddRecipeCommandTest {
    @Test
    public void constructor_nullRecipeDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRecipeCommand(null));
    }

    @Test
    public void execute_recipeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRecipeAdded modelStub = new ModelStubAcceptingRecipeAdded();
        Recipe validRecipe = new RecipeBuilder(RECIPE_EGG_PRATA).build();
        RecipeDescriptor validRecipeDescriptor = new RecipeDescriptorBuilder(validRecipe).build();

        CommandResult commandResult = new AddRecipeCommand(validRecipeDescriptor).execute(modelStub);

        assertEquals(String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRecipe), modelStub.getRecipesAdded());
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe validRecipe = new RecipeBuilder().build();
        RecipeDescriptor validRecipeDescriptor = new RecipeDescriptorBuilder(validRecipe).build();
        AddRecipeCommand addRecipeCommand = new AddRecipeCommand(validRecipeDescriptor);
        ModelStub modelStub = new ModelStubWithRecipe(validRecipe);

        Assert.assertThrows(CommandException.class,
                AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE, () -> addRecipeCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        RecipeDescriptor soupDescriptor = new RecipeDescriptorBuilder().withName("Soup").build();
        RecipeDescriptor curryDescriptor = new RecipeDescriptorBuilder().withName("Curry").build();
        AddRecipeCommand addSoupCommand = new AddRecipeCommand(soupDescriptor);
        AddRecipeCommand addCurryCommand = new AddRecipeCommand(curryDescriptor);

        // same object -> returns true
        assertTrue(addSoupCommand.equals(addSoupCommand));

        // same values -> returns true
        AddRecipeCommand addSoupCommandCopy = new AddRecipeCommand(soupDescriptor);
        assertTrue(addSoupCommand.equals(addSoupCommandCopy));

        // different types -> returns false
        assertFalse(addSoupCommand.equals(1));

        // null -> returns false
        assertFalse(addSoupCommand.equals(null));

        // different recipeDescriptor -> returns false
        assertFalse(addSoupCommand.equals(addCurryCommand));
    }
}
