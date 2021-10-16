package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GenericStringTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GenericString(null));
    }

    @Test
    public void constructor_invalidGenericString_throwsIllegalArgumentException() {
        String invalidGenericString = "";
        assertThrows(IllegalArgumentException.class, () -> new GenericString(invalidGenericString));
    }

    @Test
    public void isValidGenericString() {
        // null generic string
        assertThrows(NullPointerException.class, () -> GenericString.isValidGenericString(null));

        // invalid generic string
        assertFalse(GenericString.isValidGenericString("")); // empty string
        assertFalse(GenericString.isValidGenericString(" ")); // spaces only
        assertFalse(GenericString.isValidGenericString("^")); // only non-alphanumeric characters
        assertFalse(GenericString.isValidGenericString("peter*")); // contains non-alphanumeric characters

        // valid generic string
        assertTrue(GenericString.isValidGenericString("peter jack")); // alphabets only
        assertTrue(GenericString.isValidGenericString("12345")); // numbers only
        assertTrue(GenericString.isValidGenericString("peter the 2nd")); // alphanumeric characters
        assertTrue(GenericString.isValidGenericString("Capital Tan")); // with capital letters
        assertTrue(GenericString.isValidGenericString("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void compareTo() {
        GenericString aliceName = new GenericString("Alice");
        GenericString bobName = new GenericString("Bob");

        // name comes before alphabetically -> returns -1
        assertEquals(-1, aliceName.compareTo(bobName));

        // equal name -> returns 0
        assertEquals(0, aliceName.compareTo(aliceName));

        // name comes after alphabetically -> returns 1
        assertEquals(1, bobName.compareTo(aliceName));
    }
}
