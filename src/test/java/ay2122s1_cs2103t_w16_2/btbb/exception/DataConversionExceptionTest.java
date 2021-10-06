package ay2122s1_cs2103t_w16_2.btbb.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DataConversionExceptionTest {
    @Test
    public void testDataConversionException() {
        assertThrows(DataConversionException.class, () -> {
            throw new DataConversionException(new Exception());
        });
    }
}
