package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class QuantityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null quantity
        assertFalse(Quantity.isValidQuantity(null));

        // invalid quantity
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("-91")); // negative number
        assertFalse(Quantity.isValidQuantity("qty")); // non-numeric
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("9312 1534")); // spaces within digits
        assertFalse(Quantity.isValidQuantity("9312333333331534")); // int out of range

        // valid quantity
        assertTrue(Quantity.isValidQuantity("50"));
        assertTrue(Quantity.isValidQuantity("40000")); // large quantities
    }

    @Test
    public void isValidInternalQuantity() {
        // null quantity
        assertFalse(Quantity.isValidInternalQuantity(null));

        // invalid quantity
        assertFalse(Quantity.isValidInternalQuantity("")); // empty string
        assertFalse(Quantity.isValidInternalQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidInternalQuantity("-91")); // negative number
        assertFalse(Quantity.isValidInternalQuantity("qty")); // non-numeric
        assertFalse(Quantity.isValidInternalQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidInternalQuantity("9312 1534")); // spaces within digits
        assertFalse(Quantity.isValidInternalQuantity("9312333333331534")); // int out of range

        // valid quantity
        assertTrue(Quantity.isValidInternalQuantity("0")); // internal allows 0
        assertTrue(Quantity.isValidInternalQuantity("50"));
        assertTrue(Quantity.isValidInternalQuantity("40000")); // large quantities
    }
}
