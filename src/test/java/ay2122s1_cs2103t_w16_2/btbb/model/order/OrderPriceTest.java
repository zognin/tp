package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OrderPriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OrderPrice(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String notAFloat = "$1.00";
        assertThrows(IllegalArgumentException.class, () -> new OrderPrice(notAFloat));

        String negativePrice = "-1";
        assertThrows(IllegalArgumentException.class, () -> new OrderPrice(negativePrice));

        String largePrice = Double.toString(Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> new OrderPrice(largePrice));
    }

    @Test
    public void constructor_validPrice_returnsCorrectPrice() {
        String validInteger = "1";
        assertEquals("1.00", new OrderPrice(validInteger).toString());

        String validFloatWith2dp = "1.51";
        assertEquals("1.51", new OrderPrice(validFloatWith2dp).toString());
    }

    @Test
    public void isValidPrice() {
        // null price
        assertFalse(OrderPrice.isValidOrderPrice(null));

        // invalid price
        assertFalse(OrderPrice.isValidOrderPrice("")); // empty string
        assertFalse(OrderPrice.isValidOrderPrice(" ")); // spaces only
        assertFalse(OrderPrice.isValidOrderPrice("$1.0")); // '$' not allowed
        assertFalse(OrderPrice.isValidOrderPrice("-1")); // negative price
        assertFalse(OrderPrice.isValidOrderPrice(Double.toString(Double.MAX_VALUE))); // price too large
        assertFalse(OrderPrice.isValidOrderPrice("1.5")); // only 1dp is not allowed

        // valid price
        assertTrue(OrderPrice.isValidOrderPrice("1"));
        assertTrue(OrderPrice.isValidOrderPrice("1.51"));
    }

    @Test
    public void equals() {
        OrderPrice p1 = new OrderPrice("10");
        OrderPrice p2 = new OrderPrice("10");
        OrderPrice p3 = new OrderPrice("11");
        OrderPrice p4 = new OrderPrice("10.00");

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
