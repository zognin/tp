package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) for {@code AddRecipeCommand}.
 */
public class AddRecipeCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        Recipe validRecipe = new RecipeBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);
        RecipeDescriptor validRecipeDescriptor = new RecipeDescriptorBuilder(validRecipe).build();

        assertCommandSuccessWithTabChange(new AddRecipeCommand(validRecipeDescriptor), model,
                String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe), expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe recipeInList = model.getAddressBook().getRecipeList().get(0);
        RecipeDescriptor recipeInListDescriptor = new RecipeDescriptorBuilder(recipeInList).build();
        assertCommandFailure(new AddRecipeCommand(recipeInListDescriptor), model,
                AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }
}
