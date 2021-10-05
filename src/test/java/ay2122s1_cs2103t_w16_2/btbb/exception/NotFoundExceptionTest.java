
package ay2122s1_cs2103t_w16_2.btbb.exception;

import static ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException.NOT_FOUND_EXCEPTION_MESSAGE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NotFoundExceptionTest {
    private static final String NOT_FOUND_EXCEPTION_INPUT = "random input";
    private static final String EXPECTED_EXCEPTION_MESSAGE = String.format(
            NOT_FOUND_EXCEPTION_MESSAGE_FORMAT, NOT_FOUND_EXCEPTION_INPUT
    );

    @Test
    public void testNotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(NOT_FOUND_EXCEPTION_INPUT);
        });

        try {
            throw new NotFoundException(NOT_FOUND_EXCEPTION_INPUT);
        } catch (NotFoundException e) {
            assertEquals(e.getMessage(), EXPECTED_EXCEPTION_MESSAGE);
        }
    }
}
