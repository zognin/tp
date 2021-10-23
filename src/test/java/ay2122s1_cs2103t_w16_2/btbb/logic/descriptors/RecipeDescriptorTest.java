package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE_PIE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF_STEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_BEEF_STEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;

public class RecipeDescriptorTest {
    @Test
    public void toModelType_nullFields_throwsNullPointerException() {
        // All fields are null
        assertThrows(NullPointerException.class, () -> new RecipeDescriptor().toModelType());

        // Name field is null
        RecipeDescriptor nullNameDescriptor = new RecipeDescriptorBuilder().build();
        nullNameDescriptor.setName(null);
        assertThrows(NullPointerException.class, nullNameDescriptor::toModelType);

        // Ingredients field is null
        RecipeDescriptor nullIngredientsDescriptor = new RecipeDescriptorBuilder().build();
        nullIngredientsDescriptor.setRecipeIngredients(null);
        assertThrows(NullPointerException.class, nullIngredientsDescriptor::toModelType);

        // Price field is null
        RecipeDescriptor nullPriceDescriptor = new RecipeDescriptorBuilder().build();
        nullPriceDescriptor.setRecipePrice(null);
        assertThrows(NullPointerException.class, nullPriceDescriptor::toModelType);
    }

    @Test
    public void toModelType_validDescriptor_success() {
        Recipe expectedModelRecipe = new RecipeBuilder().build();
        RecipeDescriptor validRecipeDescriptor = new RecipeDescriptorBuilder(expectedModelRecipe).build();
        assertEquals(expectedModelRecipe, validRecipeDescriptor.toModelType());
    }

    @Test
    public void toModelTypeFrom() {
        // All fields in descriptor are non null
        Recipe expectedModelRecipe = new RecipeBuilder().build();
        RecipeDescriptor validRecipeDescriptor = new RecipeDescriptorBuilder(expectedModelRecipe).build();
        assertEquals(expectedModelRecipe, validRecipeDescriptor.toModelTypeFrom(expectedModelRecipe));

        // Name field in descriptor is null
        expectedModelRecipe = new RecipeBuilder().build();
        validRecipeDescriptor = new RecipeDescriptorBuilder(expectedModelRecipe).build();
        validRecipeDescriptor.setName(null);
        assertEquals(expectedModelRecipe, validRecipeDescriptor.toModelTypeFrom(expectedModelRecipe));

        // Ingredients field in descriptor is null
        expectedModelRecipe = new RecipeBuilder().withRecipeIngredients(
                new RecipeIngredientList(new ArrayList<>())).build();
        validRecipeDescriptor = new RecipeDescriptorBuilder(expectedModelRecipe).build();
        validRecipeDescriptor.setRecipeIngredients(null);
        assertEquals(expectedModelRecipe, validRecipeDescriptor.toModelTypeFrom(expectedModelRecipe));

        // Price field in descriptor is null
        expectedModelRecipe = new RecipeBuilder().withPrice(VALID_PRICE_1).build();
        validRecipeDescriptor = new RecipeDescriptorBuilder(expectedModelRecipe).build();
        validRecipeDescriptor.setRecipePrice(null);
        assertEquals(expectedModelRecipe, validRecipeDescriptor.toModelTypeFrom(expectedModelRecipe));
    }

    @Test
    public void equals() {
        // same values -> returns true
        RecipeDescriptor descriptorWithSameValues = new RecipeDescriptor(DESC_APPLE_PIE);
        assertTrue(DESC_APPLE_PIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE_PIE.equals(DESC_APPLE_PIE));

        // null -> returns false
        assertFalse(DESC_APPLE_PIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE_PIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE_PIE.equals(DESC_BEEF_STEW));

        // different name -> returns false
        RecipeDescriptor editedRecipeDescriptor = new RecipeDescriptorBuilder(DESC_APPLE_PIE)
                .withName(VALID_RECIPE_NAME_BEEF_STEW).build();
        assertFalse(DESC_APPLE_PIE.equals(editedRecipeDescriptor));

        // different ingredients -> returns false
        editedRecipeDescriptor = new RecipeDescriptorBuilder(DESC_APPLE_PIE)
                .withRecipeIngredients(List.of(new Ingredient(new GenericString(VALID_INGREDIENT_NAME_BEEF),
                        new Quantity(VALID_QUANTITY_BEEF), new GenericString(VALID_UNIT_BEEF)))).build();
        assertFalse(DESC_APPLE_PIE.equals(editedRecipeDescriptor));

        // different price -> returns false
        editedRecipeDescriptor = new RecipeDescriptorBuilder(DESC_APPLE_PIE).withPrice(VALID_PRICE_2).build();
        assertFalse(DESC_APPLE_PIE.equals(editedRecipeDescriptor));
    }
}
