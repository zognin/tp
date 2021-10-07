package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.storage.JsonAdaptedIngredient.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BUTTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;

public class JsonAdaptedIngredientTest {
    private static final String INVALID_INGREDIENT_NAME = "Ch@co!late";
    private static final String INVALID_QUANTITY = "-20";
    private static final String INVALID_UNIT = "gr@ms";

    private static final String VALID_INGREDIENT_NAME = BUTTER.getName().toString();
    private static final String VALID_QUANTITY = BUTTER.getQuantity().toString();
    private static final String VALID_UNIT = BUTTER.getUnit().toString();

    @Test
    public void toModelType_validIngredientDetails_returnsIngredient() throws Exception {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(BUTTER);
        assertEquals(BUTTER, ingredient.toModelType());
    }

    @Test
    public void toModelType_invalidIngredientName_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(INVALID_INGREDIENT_NAME, VALID_QUANTITY, VALID_UNIT);
        String expectedMessage = IngredientName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_nullIngredientName_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(null, VALID_QUANTITY, VALID_UNIT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IngredientName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(VALID_INGREDIENT_NAME, INVALID_QUANTITY, VALID_UNIT);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(VALID_INGREDIENT_NAME, null, VALID_UNIT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_invalidUnit_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient =
                new JsonAdaptedIngredient(VALID_INGREDIENT_NAME, VALID_QUANTITY, INVALID_UNIT);
        String expectedMessage = Unit.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }

    @Test
    public void toModelType_nullUnit_throwsIllegalValueException() {
        JsonAdaptedIngredient ingredient = new JsonAdaptedIngredient(VALID_INGREDIENT_NAME, VALID_QUANTITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Unit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, ingredient::toModelType);
    }
}
