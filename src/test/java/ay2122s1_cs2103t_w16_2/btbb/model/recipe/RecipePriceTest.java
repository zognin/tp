package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class RecipePriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipePrice(null));
    }

    @Test
    public void constructor_invalidRecipePrice_throwsIllegalArgumentException() {
        String notAFloat = "$1.00";
        assertThrows(IllegalArgumentException.class, () -> new RecipePrice(notAFloat));

        String negativePrice = "-1";
        assertThrows(IllegalArgumentException.class, () -> new RecipePrice(negativePrice));

        String priceMoreThan2500 = "2501";
        assertThrows(IllegalArgumentException.class, () -> new RecipePrice(priceMoreThan2500));
    }

    @Test
    public void constructor_validRecipePrice_returnsCorrectRecipePrice() {
        String validInteger = "1";
        assertEquals("1.00", new RecipePrice(validInteger).toString());

        String validFloatWith2dp = "2499.99";
        assertEquals("2499.99", new RecipePrice(validFloatWith2dp).toString());
    }

    @Test
    public void isValidRecipePrice() {
        // null price
        assertFalse(RecipePrice.isValidRecipePrice(null));

        // invalid price
        assertFalse(RecipePrice.isValidRecipePrice("")); // empty string
        assertFalse(RecipePrice.isValidRecipePrice(" ")); // spaces only
        assertFalse(RecipePrice.isValidRecipePrice("$1.0")); // '$' not allowed
        assertFalse(RecipePrice.isValidRecipePrice("-1")); // negative price
        assertFalse(RecipePrice.isValidRecipePrice("2501")); // price too large
        assertFalse(RecipePrice.isValidRecipePrice("1.5")); // only 1dp is not allowed

        // valid price
        assertTrue(RecipePrice.isValidRecipePrice("1"));
        assertTrue(RecipePrice.isValidRecipePrice("2499.99"));
    }

    @Test
    public void multiplyPriceByQuantity() {
        RecipePrice recipePrice = new RecipePrice("1");
        Quantity quantity = new Quantity("2");

        // multiply price by 2 -> returns OrderPrice with value 2
        assertEquals(new OrderPrice("2"), recipePrice.multiplyRecipePriceByQuantity(quantity));

        // multiply price by 0 -> returns OrderPrice with value 0
        quantity = new Quantity("0");
        assertEquals(new OrderPrice("0"), recipePrice.multiplyRecipePriceByQuantity(quantity));
    }

    @Test
    public void equals() {
        RecipePrice p1 = new RecipePrice("10");
        RecipePrice p2 = new RecipePrice("10");
        RecipePrice p3 = new RecipePrice("11");
        RecipePrice p4 = new RecipePrice("10.00");

        // same values -> returns true
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p4));

        // same object -> returns true
        assertTrue(p1.equals(p1));

        // null -> returns false
        assertFalse(p1.equals(null));

        // different price -> returns false
        assertFalse(p1.equals(p3));
    }
}
