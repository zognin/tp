package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class AddIngredientCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newIngredient_success() {
        Ingredient validIngredient = new IngredientBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addIngredient(validIngredient);
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();

        assertCommandSuccessWithTabChange(new AddIngredientCommand(validIngredientDescriptor), model,
                String.format(AddIngredientCommand.MESSAGE_SUCCESS, validIngredient), expectedModel, UiTab.INVENTORY);
    }

    @Test
    public void execute_duplicateIngredient_throwsCommandException() {
        Ingredient ingredientInList = model.getAddressBook().getIngredientList().get(0);
        IngredientDescriptor ingredientInListDescriptor = new IngredientDescriptorBuilder(ingredientInList).build();
        assertCommandFailure(new AddIngredientCommand(ingredientInListDescriptor), model,
                AddIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }
}
