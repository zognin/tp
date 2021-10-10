package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RecipeIngredientListTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecipeIngredientList(null));
    }
}
