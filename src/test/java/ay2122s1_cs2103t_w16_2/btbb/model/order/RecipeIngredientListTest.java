package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BREAD;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BUTTER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;

class RecipeIngredientListTest {
    private final RecipeIngredientList recipeIngredientList = new RecipeIngredientList(new ArrayList<>());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeIngredientList(null));
    }

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recipeIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(recipeIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        recipeIngredientList.getIngredients().add(APPLE);
        assertTrue(recipeIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        recipeIngredientList.getIngredients().add(APPLE);
        Ingredient appleWithEditedSameUnit = new IngredientBuilder(APPLE).withUnit("whole").build();
        Ingredient appleWithEditedDifferentQuantity = new IngredientBuilder(APPLE).withQuantity("1000").build();
        assertTrue(recipeIngredientList.contains(appleWithEditedSameUnit));
        assertTrue(recipeIngredientList.contains(appleWithEditedDifferentQuantity));
    }

    @Test
    public void isValidRecipeIngredientList() {
        // null list
        assertFalse(RecipeIngredientList.isValidRecipeIngredientList(null));

        // invalid list
        assertFalse(RecipeIngredientList.isValidRecipeIngredientList(new ArrayList<>())); // empty list

        // valid list
        assertTrue(RecipeIngredientList.isValidRecipeIngredientList(List.of(AVOCADO))); // single element list
        assertTrue(RecipeIngredientList.isValidRecipeIngredientList(List.of(BREAD, BUTTER))); // multi element list
    }

    @Test
    public void isValidInternalRecipeIngredientList() {
        // null list
        assertFalse(RecipeIngredientList.isValidInternalRecipeIngredientList(null));

        // valid list
        assertTrue(RecipeIngredientList
                .isValidInternalRecipeIngredientList(new ArrayList<>())); // zero element list allowed internally
        assertTrue(RecipeIngredientList
                .isValidInternalRecipeIngredientList(List.of(AVOCADO))); // single element list
        assertTrue(RecipeIngredientList
                .isValidInternalRecipeIngredientList(List.of(BREAD, BUTTER))); // multi element list
    }
}
