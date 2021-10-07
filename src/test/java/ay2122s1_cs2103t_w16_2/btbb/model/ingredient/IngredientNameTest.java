package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IngredientNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IngredientName(null));
    }

    @Test
    public void constructor_invalidIngredientName_throwsIllegalArgumentException() {
        String invalidIngredientName = "";
        assertThrows(IllegalArgumentException.class, () -> new IngredientName(invalidIngredientName));
    }

    @Test
    public void isValidIngredientName() {
        // null ingredient name
        assertThrows(NullPointerException.class, () -> IngredientName.isValidIngredientName(null));

        // invalid ingredient name
        assertFalse(IngredientName.isValidIngredientName("")); // empty string
        assertFalse(IngredientName.isValidIngredientName(" ")); // spaces only
        assertFalse(IngredientName.isValidIngredientName("^")); // only non-alphanumeric characters
        assertFalse(IngredientName.isValidIngredientName("duck*")); // contains non-alphanumeric characters

        // valid ingredient name
        assertTrue(IngredientName.isValidIngredientName("potato")); // alphabets only
        assertTrue(IngredientName.isValidIngredientName("11111")); // numbers only
        assertTrue(IngredientName.isValidIngredientName("Vitamin B3")); // alphanumeric characters
        assertTrue(IngredientName.isValidIngredientName("Sesame Seeds")); // with capital letters
        // long ingredient names
        assertTrue(IngredientName.isValidIngredientName("homemade pickled vegetables with sauce"));
    }
}
