package ay2122s1_cs2103t_w16_2.btbb.model.order;

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

        String validFloatWith1dp = "1.5";
        assertEquals("1.50", new Price(validFloatWith1dp).toString());

        String validFloatWith2dp = "1.51";
        assertEquals("1.51", new Price(validFloatWith2dp).toString());

        String validFloatWith3dp = "1.519"; // test whether it's rounded up
        assertEquals("1.52", new Price(validFloatWith3dp).toString());

        String validFloatWith4dp = "1.5141"; // test whether it's rounded down
        assertEquals("1.51", new Price(validFloatWith4dp).toString());
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

        // valid price
        assertTrue(Price.isValidPrice("1"));
        assertTrue(Price.isValidPrice("1.5"));
        assertTrue(Price.isValidPrice("1.51"));
        assertTrue(Price.isValidPrice("1.501"));
    }
}
