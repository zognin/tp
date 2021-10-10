package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String emptyDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(emptyDeadline));

        String invalidFormat = "12/12/2025 1900";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidFormat));

        String invalidFormat2 = "2025-12-12 1900";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidFormat2));

        String missingDate = "1900";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(missingDate));

        String missingTime = "12-12-2025";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(missingTime));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertFalse(Deadline.isValidDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("12/12/2025 1900")); // invalid format ('/' not allowed)
        assertFalse(Deadline.isValidDeadline("2025-12-12 1900")); // invalid format (wrong date format)
        assertFalse(Deadline.isValidDeadline("1900")); // missing date
        assertFalse(Deadline.isValidDeadline("12-12-2025")); // missing time

        // valid deadline
        assertTrue(Deadline.isValidDeadline("12-12-2025 1900"));
        assertTrue(Deadline.isValidDeadline("12-09-2025 2359"));
    }

    @Test
    public void toJsonString() {
        assertEquals("12-12-2025 1900", new Deadline("12-12-2025 1900").toJsonStorageString());
        assertEquals("12-09-2025 2359", new Deadline("12-09-2025 2359").toJsonStorageString());
    }
}
