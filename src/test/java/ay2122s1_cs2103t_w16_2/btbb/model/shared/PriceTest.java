package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String notAFloat = "$1.00";
        assertThrows(IllegalArgumentException.class, () -> new Price(notAFloat));

        String negativePrice = "-1";
        assertThrows(IllegalArgumentException.class, () -> new Price(negativePrice));

        String largePrice = Double.toString(Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> new Price(largePrice));
    }

    @Test
    public void constructor_validPrice_returnsCorrectPrice() {
        String validInteger = "1";
        assertEquals("1.00", new Price(validInteger).toString());

        String validFloatWith2dp = "1.51";
        assertEquals("1.51", new Price(validFloatWith2dp).toString());
    }

    @Test
    public void multiplyPriceByQuantity() {
        Price price = new Price("1");
        Quantity quantity = new Quantity("2");

        // multiply price by 2 -> returns Price with value 2
        assertEquals(new Price("2"), price.multiplyPriceByQuantity(quantity));

        // multiply price by 0 -> returns Price with value 0
        quantity = new Quantity("0");
        assertEquals(new Price("0"), price.multiplyPriceByQuantity(quantity));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertFalse(Price.isValidPrice(null));

        // invalid price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("$1.0")); // '$' not allowed
        assertFalse(Price.isValidPrice("-1")); // negative price
        assertFalse(Price.isValidPrice(Double.toString(Double.MAX_VALUE))); // price too large
        assertFalse(Price.isValidPrice("1.5")); // only 1dp is not allowed

        // valid price
        assertTrue(Price.isValidPrice("1"));
        assertTrue(Price.isValidPrice("1.51"));
    }

    @Test
    public void equals() {
        Price p1 = new Price("10");
        Price p2 = new Price("10");
        Price p3 = new Price("11");
        Price p4 = new Price("10.00");

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
