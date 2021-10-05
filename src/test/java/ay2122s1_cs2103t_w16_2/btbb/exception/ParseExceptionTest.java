package ay2122s1_cs2103t_w16_2.btbb.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParseExceptionTest {
    private static final String PARSE_EXCEPTION_MESSAGE = "This is a ParseException.";

    @Test
    public void testParseException() {
        assertThrows(ParseException.class, () -> {
            throw new ParseException(PARSE_EXCEPTION_MESSAGE);
        });

        assertThrows(ParseException.class, () -> {
            throw new ParseException(PARSE_EXCEPTION_MESSAGE, new Throwable());
        });
    }
}
