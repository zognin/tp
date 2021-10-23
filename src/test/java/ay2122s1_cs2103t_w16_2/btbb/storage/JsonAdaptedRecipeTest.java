package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.CHICKEN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.RICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class JsonAdaptedRecipeTest {
    private static final String INVALID_RECIPE_NAME = "Egg Pr@ta";
    private static final String INVALID_RECIPE_PRICE = "$2.40";

    private static final String VALID_RECIPE_NAME = RECIPE_EGG_PRATA.getName().toString();
    private static final String VALID_RECIPE_PRICE = RECIPE_EGG_PRATA.getRecipePrice().toString();
    private static final List<JsonAdaptedIngredient> VALID_RECIPE_INGREDIENT_LIST = List.of(
            new JsonAdaptedIngredient(
                    CHICKEN.getName().toString(), new Quantity("1").toString(), CHICKEN.getUnit().toString()),
            new JsonAdaptedIngredient(
                    RICE.getName().toString(), new Quantity("100").toString(), RICE.getUnit().toString()));

    @Test
    public void toModelType_validDetails_returnsRecipe() throws IllegalValueException {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(RECIPE_EGG_PRATA);
        assertEquals(recipe.toModelType(), RECIPE_EGG_PRATA);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(INVALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST,
                VALID_RECIPE_PRICE);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(null, VALID_RECIPE_INGREDIENT_LIST,
                VALID_RECIPE_PRICE);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST,
                INVALID_RECIPE_PRICE);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedRecipe recipe = new JsonAdaptedRecipe(VALID_RECIPE_NAME, VALID_RECIPE_INGREDIENT_LIST, null);
        assertThrows(IllegalValueException.class, recipe::toModelType);
    }
}
