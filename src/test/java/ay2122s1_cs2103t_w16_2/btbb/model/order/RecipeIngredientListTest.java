package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class RecipeIngredientListTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeIngredientList(null));
    }

    @Test
    public void constructor_invalidRecipeIngredientList_throwsIllegalArgumentException() {
        String emptyList = "";
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(emptyList));

        String missingName = "-1-whole"; // missing name
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(missingName));

        String missingQuantity = "Eggs--whole"; // missing quantity
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(missingQuantity));

        String missingUnit = "Eggs-1-"; // missing unit
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(missingUnit));

        String invalidFormat = "whole-Eggs-1";
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(invalidFormat));

        String notCommaSeparated = "Eggs-1-whole Chicken-1-whole"; // not comma separated
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(notCommaSeparated));

        String notCommaSeparated2 = "Eggs-1-whole | Chicken-1-whole"; // not comma separated
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(notCommaSeparated2));

        String firstIngredientWrongFormat = "Eggs-whole-whole, Chicken-whole-whole"; // last ingredient wrong format
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(firstIngredientWrongFormat));

        String lastIngredientWrongFormat = "Eggs-1-whole, Chicken-whole-whole"; // last ingredient wrong format
        assertThrows(IllegalArgumentException.class, () -> new RecipeIngredientList(lastIngredientWrongFormat));
    }

    @Test
    public void isValidRecipeIngredientList() {
        List<String> inValidIngredients = List.of("-1-whole", "Eggs--whole", "Almond-100-");
        List<String> validIngredients = List.of("Chicken-1-whole", "Eggs-1-whole", "Almond-100-grams");

        // null ingredients
        assertFalse(RecipeIngredientList.isValidRecipeIngredientList(null));

        // invalid ingredients
        inValidIngredients.forEach(inValidIngredient -> {
            assertFalse(RecipeIngredientList.isValidRecipeIngredientList(inValidIngredient));
        });

        // valid ingredients
        validIngredients.forEach(validIngredient -> {
            assertTrue(RecipeIngredientList.isValidRecipeIngredientList(validIngredient));
        });

        // valid ingredient list
        assertTrue(RecipeIngredientList.isValidRecipeIngredientList(String.join(", ", validIngredients)));
    }

    @Test
    public void toJsonStorageString() {
        List<String> validIngredients = List.of("Chicken-1-whole", "Eggs-1-whole", "Almond-100-grams");

        validIngredients.forEach(validIngredient -> {
            assertEquals(validIngredient, new RecipeIngredientList(validIngredient).toJsonStorageString());
        });
    }
}
