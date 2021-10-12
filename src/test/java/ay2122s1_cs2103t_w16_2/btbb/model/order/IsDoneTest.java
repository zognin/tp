package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsDoneTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IsDone(null));
    }

    @Test
    public void constructor_invalidIsDone_throwsIllegalArgumentException() {
        String truncatedInput = "y";
        assertThrows(IllegalArgumentException.class, () -> new IsDone(truncatedInput));

        String capitalisedInput = "NO";
        assertThrows(IllegalArgumentException.class, () -> new IsDone(capitalisedInput));
    }

    @Test
    public void constructor_validIsDone_returnsCorrectIsDone() {
        String validYes = "yes";
        assertEquals("yes", new IsDone(validYes).toString());

        String validNo = "no";
        assertEquals("no", new IsDone(validNo).toString());
    }

    @Test
    public void isValidIsDone() {
        // null isDone
        assertThrows(NullPointerException.class, () -> IsDone.isValidIsDone(null));

        // invalid isDone
        assertFalse(IsDone.isValidIsDone("")); // empty string
        assertFalse(IsDone.isValidIsDone(" ")); // spaces only
        assertFalse(IsDone.isValidIsDone("yes!")); // '!' not allowed
        assertFalse(IsDone.isValidIsDone("y")); // short form not allowed
        assertFalse(IsDone.isValidIsDone("YES")); // Case-sensitive

        // valid isDone (only 'yes' and 'no' are valid)
        assertTrue(IsDone.isValidIsDone("yes"));
        assertTrue(IsDone.isValidIsDone("no"));
    }
}
