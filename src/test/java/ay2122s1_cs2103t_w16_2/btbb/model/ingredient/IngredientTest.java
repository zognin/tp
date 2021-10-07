package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;

public class IngredientTest {
    @Test
    public void isSameIngredient() {
        // same object -> returns true
        assertTrue(AVOCADO.isSameIngredient(AVOCADO));

        // null -> returns false
        assertFalse(AVOCADO.isSameIngredient(null));

        // same ingredient name and unit, all other attributes different -> returns true
        Ingredient editedAvocado = new IngredientBuilder(AVOCADO).withQuantity(VALID_QUANTITY_BEEF).build();
        assertTrue(AVOCADO.isSameIngredient(editedAvocado));

        // different ingredient name, all other attributes same -> returns false
        editedAvocado = new IngredientBuilder(AVOCADO).withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        assertFalse(AVOCADO.isSameIngredient(editedAvocado));

        // different ingredient name, all other attributes same -> returns false
        editedAvocado = new IngredientBuilder(AVOCADO).withUnit(VALID_UNIT_BEEF).build();
        assertFalse(AVOCADO.isSameIngredient(editedAvocado));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Ingredient avocadoCopy = new IngredientBuilder(AVOCADO).build();
        assertTrue(AVOCADO.equals(avocadoCopy));

        // same object -> returns true
        assertTrue(AVOCADO.equals(AVOCADO));

        // null -> returns false
        assertFalse(AVOCADO.equals(null));

        // different type -> returns false
        assertFalse(AVOCADO.equals(5));

        // different ingredient -> returns false
        assertFalse(AVOCADO.equals(BEEF));

        // different ingredient name -> returns false
        Ingredient editedAvocado = new IngredientBuilder(AVOCADO)
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        assertFalse(AVOCADO.equals(editedAvocado));

        // different quantity -> returns false
        editedAvocado = new IngredientBuilder(AVOCADO).withQuantity(VALID_QUANTITY_BEEF).build();
        assertFalse(AVOCADO.equals(editedAvocado));

        // different unit -> returns false
        editedAvocado = new IngredientBuilder(AVOCADO).withUnit(VALID_UNIT_BEEF).build();
        assertFalse(AVOCADO.equals(editedAvocado));
    }
}
