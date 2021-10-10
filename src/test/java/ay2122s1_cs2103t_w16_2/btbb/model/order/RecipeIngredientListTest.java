package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BREAD;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BUTTER;
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
    public void isValidRecipeIngredientList() {
        // null list
        assertFalse(RecipeIngredientList.isValidRecipeIngredientList(null));

        // invalid list
        assertFalse(RecipeIngredientList.isValidRecipeIngredientList(List.of())); // empty list

        // valid list
        assertTrue(RecipeIngredientList.isValidRecipeIngredientList(List.of(AVOCADO))); // single element list
        assertTrue(RecipeIngredientList.isValidRecipeIngredientList(List.of(BREAD, BUTTER))); // multi element list
    }
}
