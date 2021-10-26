package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStub;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingIngredientAdded;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubWithIngredient;

public class AddIngredientCommandTest {
    @Test
    public void constructor_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddIngredientCommand(null));
    }

    @Test
    public void execute_ingredientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingIngredientAdded modelStub = new ModelStubAcceptingIngredientAdded();
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();

        CommandResult commandResult = new AddIngredientCommand(validIngredientDescriptor).execute(modelStub);

        assertEquals(String.format(AddIngredientCommand.MESSAGE_SUCCESS, validIngredient),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIngredient), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_duplicateIngredient_throwsCommandException() {
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientBuilder = new IngredientDescriptorBuilder(validIngredient).build();
        AddIngredientCommand addIngredientCommand = new AddIngredientCommand(validIngredientBuilder);
        ModelStub modelStub = new ModelStubWithIngredient(validIngredient);

        assertThrows(CommandException.class,
                MESSAGE_DUPLICATE_INGREDIENT, () -> addIngredientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        IngredientDescriptor appleDescriptor = new IngredientDescriptorBuilder().withIngredientName("Apple").build();
        IngredientDescriptor beefDescriptor = new IngredientDescriptorBuilder().withIngredientName("Beef").build();
        AddIngredientCommand addAppleCommand = new AddIngredientCommand(appleDescriptor);
        AddIngredientCommand addBeefCommand = new AddIngredientCommand(beefDescriptor);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddIngredientCommand addAppleCommandCopy = new AddIngredientCommand(appleDescriptor);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(addAppleCommand.equals(addBeefCommand));
    }
}

