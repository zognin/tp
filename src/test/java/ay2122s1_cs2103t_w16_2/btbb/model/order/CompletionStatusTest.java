package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompletionStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompletionStatus(null));
    }

    @Test
    public void constructor_invalidCompletionStatus_throwsIllegalArgumentException() {
        String truncatedInput = "y";
        assertThrows(IllegalArgumentException.class, () -> new CompletionStatus(truncatedInput));

        String capitalisedInput = "no!";
        assertThrows(IllegalArgumentException.class, () -> new CompletionStatus(capitalisedInput));
    }

    @Test
    public void constructor_validICompletionStatus_returnsCorrectCompletionStatus() {
        String validYes = "yes";
        assertEquals("yes", new CompletionStatus(validYes).toString());

        String validNo = "no";
        assertEquals("no", new CompletionStatus(validNo).toString());
    }

    @Test
    public void isValidCompletionStatus() {
        // null completionStatus
        assertThrows(NullPointerException.class, () -> CompletionStatus.isValidCompletionStatus(null));

        // invalid completionStatus
        assertFalse(CompletionStatus.isValidCompletionStatus("")); // empty string
        assertFalse(CompletionStatus.isValidCompletionStatus(" ")); // spaces only
        assertFalse(CompletionStatus.isValidCompletionStatus("yes!")); // '!' not allowed
        assertFalse(CompletionStatus.isValidCompletionStatus("y")); // short form not allowed

        // valid completionStatus
        assertTrue(CompletionStatus.isValidCompletionStatus("yes"));
        assertTrue(CompletionStatus.isValidCompletionStatus("YES")); // Case-insensitive
        assertTrue(CompletionStatus.isValidCompletionStatus("yES")); // Case-sensitive
        assertTrue(CompletionStatus.isValidCompletionStatus("no"));
    }

    @Test
    public void compareTo() {
        CompletionStatus completed = new CompletionStatus(true);
        CompletionStatus notCompleted = new CompletionStatus(false);

        assertEquals(0, completed.compareTo(completed));
        assertTrue(completed.compareTo(notCompleted) > 0);
        assertTrue(notCompleted.compareTo(completed) < 0);
    }
}
