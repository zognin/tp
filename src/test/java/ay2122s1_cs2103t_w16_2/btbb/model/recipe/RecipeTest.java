package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.INGREDIENT_LIST_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_LAKSA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;

public class RecipeTest {
    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(RECIPE_EGG_PRATA.isSameRecipe(RECIPE_EGG_PRATA));

        // null -> returns false
        assertFalse(RECIPE_EGG_PRATA.isSameRecipe(null));

        // different recipe name, all other attributes same -> returns false
        Recipe editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withName(RECIPE_LAKSA.getName().toString()).build();
        assertFalse(RECIPE_EGG_PRATA.isSameRecipe(editedPrata));

        // different recipe ingredients, all other attributes same -> returns false
        editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withRecipeIngredients(INGREDIENT_LIST_LAKSA).build();
        assertFalse(RECIPE_EGG_PRATA.isSameRecipe(editedPrata));

        // different recipe price, all other attributes same -> returns false
        editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withRecipePrice(VALID_RECIPE_PRICE_1).build();
        assertFalse(RECIPE_EGG_PRATA.isSameRecipe(editedPrata));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Recipe eggPrataCopy = new RecipeBuilder(RECIPE_EGG_PRATA).build();
        assertTrue(RECIPE_EGG_PRATA.equals(eggPrataCopy));

        // same object -> returns true
        assertTrue(RECIPE_EGG_PRATA.equals(RECIPE_EGG_PRATA));

        // null -> returns false
        assertFalse(RECIPE_EGG_PRATA.equals(null));

        // different type -> returns false
        assertFalse(RECIPE_EGG_PRATA.equals(5));

        // different recipe -> returns false
        assertFalse(RECIPE_EGG_PRATA.equals(RECIPE_LAKSA));

        // different name -> returns false
        Recipe editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withName(VALID_RECIPE_NAME_CHICKEN_RICE).build();
        assertFalse(RECIPE_EGG_PRATA.equals(editedPrata));

        // different recipe ingredient list -> returns false
        editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withRecipeIngredients(INGREDIENT_LIST_LAKSA).build();
        assertFalse(RECIPE_EGG_PRATA.equals(editedPrata));

        // different price -> returns false
        editedPrata = new RecipeBuilder(RECIPE_EGG_PRATA).withRecipePrice(VALID_RECIPE_PRICE_1).build();
        assertFalse(RECIPE_EGG_PRATA.equals(editedPrata));
    }

    @Test
    public void compareTo() {
        // name comes before alphabetically -> returns -1
        assertEquals(-7, RECIPE_EGG_PRATA.compareTo(RECIPE_LAKSA));

        // equal name -> returns 0
        assertEquals(0, RECIPE_EGG_PRATA.compareTo(RECIPE_EGG_PRATA));

        // name comes after alphabetically -> returns 1
        assertEquals(7, RECIPE_LAKSA.compareTo(RECIPE_EGG_PRATA));
    }
}
