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
        assertFalse(Deadline.isValidDeadline("29-02-2022 1900")); // not leap year
        assertFalse(Deadline.isValidDeadline("31-04-2022 1900")); // day does not exist in April

        // valid deadline
        assertTrue(Deadline.isValidDeadline("12-12-2025 1900"));
        assertTrue(Deadline.isValidDeadline("12-09-2025 2359"));
        assertTrue(Deadline.isValidDeadline("29-02-2024 2359")); // leap year
    }

    @Test
    public void isValidInternalDeadline() {
        // null deadline
        assertFalse(Deadline.isValidInternalDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidInternalDeadline("")); // empty string
        assertFalse(Deadline.isValidInternalDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidInternalDeadline("12/12/2019 1900")); // invalid format ('/' not allowed)
        assertFalse(Deadline.isValidInternalDeadline("12/12/2025 1900")); // invalid format ('/' not allowed)
        assertFalse(Deadline.isValidInternalDeadline("2025-12-12 1900")); // invalid format (wrong date format)
        assertFalse(Deadline.isValidInternalDeadline("1900")); // missing date
        assertFalse(Deadline.isValidInternalDeadline("12-12-2025")); // missing time
        assertFalse(Deadline.isValidInternalDeadline("29-02-2022 1900")); // not leap year
        assertFalse(Deadline.isValidInternalDeadline("31-04-2022 1900")); // day does not exist in April

        // valid deadline
        assertTrue(Deadline.isValidInternalDeadline("12-12-0000 1900")); // past date
        assertTrue(Deadline.isValidInternalDeadline("12-12-2019 1900")); // past date
        assertTrue(Deadline.isValidInternalDeadline("12-12-2025 1900"));
        assertTrue(Deadline.isValidInternalDeadline("12-09-2025 2359"));
        assertTrue(Deadline.isValidInternalDeadline("29-02-2020 2359")); // past leap year
        assertTrue(Deadline.isValidInternalDeadline("29-02-2024 2359")); // leap year
    }

    @Test
    public void toJsonString() {
        assertEquals("12-12-2019 1900", new Deadline("12-12-2019 1900").toJsonStorageString());
        assertEquals("12-12-2025 1900", new Deadline("12-12-2025 1900").toJsonStorageString());
        assertEquals("12-09-2025 2359", new Deadline("12-09-2025 2359").toJsonStorageString());
        assertEquals("29-02-2024 2359", new Deadline("29-02-2024 2359").toJsonStorageString());
    }
}
