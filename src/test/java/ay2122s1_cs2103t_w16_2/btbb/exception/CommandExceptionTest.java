package ay2122s1_cs2103t_w16_2.btbb.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CommandExceptionTest {
    private static final String COMMAND_EXCEPTION_MESSAGE = "This is a CommandException.";

    @Test
    public void testCommandException() {
        assertThrows(CommandException.class, () -> {
            throw new CommandException(COMMAND_EXCEPTION_MESSAGE);
        });

        assertThrows(CommandException.class, () -> {
            throw new CommandException(COMMAND_EXCEPTION_MESSAGE, new Throwable());
        });
    }
}
