package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UnitTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Unit(null));
    }

    @Test
    public void constructor_invalidUnit_throwsIllegalArgumentException() {
        String invalidUnit = "";
        assertThrows(IllegalArgumentException.class, () -> new Unit(invalidUnit));
    }

    @Test
    public void isValidUnit() {
        // null unit
        assertThrows(NullPointerException.class, () -> Unit.isValidUnit(null));

        // invalid unit
        assertFalse(Unit.isValidUnit("")); // empty string
        assertFalse(Unit.isValidUnit(" ")); // spaces only
        assertFalse(Unit.isValidUnit("^")); // only non-alphanumeric characters
        assertFalse(Unit.isValidUnit("pork*")); // contains non-alphanumeric characters

        // valid unit
        assertTrue(Unit.isValidUnit("grams")); // alphabets only
        assertTrue(Unit.isValidUnit("11111")); // numbers only
        assertTrue(Unit.isValidUnit("1 Dozen")); // alphanumeric characters
        assertTrue(Unit.isValidUnit("Dozen")); // with capital letters
        assertTrue(Unit.isValidUnit("Imperial tablespoons")); // long ingredient names
    }
}
