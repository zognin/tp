package ay2122s1_cs2103t_w16_2.btbb.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IllegalValueExceptionTest {
    private static final String ILLEGAL_VALUE_EXCEPTION_MESSAGE = "This is a IllegalValueException.";

    @Test
    public void testIllegalValueException() {
        assertThrows(CommandException.class, () -> {
            throw new CommandException(ILLEGAL_VALUE_EXCEPTION_MESSAGE);
        });

        assertThrows(CommandException.class, () -> {
            throw new CommandException(ILLEGAL_VALUE_EXCEPTION_MESSAGE, new Throwable());
        });
    }
}
