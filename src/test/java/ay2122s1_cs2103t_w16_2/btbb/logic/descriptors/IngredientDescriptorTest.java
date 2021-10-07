package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;

public class IngredientDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        IngredientDescriptor descriptorWithSameValues = new IngredientDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // null -> returns false
        assertFalse(DESC_APPLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_BEEF));

        // different ingredient name -> returns false
        IngredientDescriptor editedIngredientDescriptor = new IngredientDescriptorBuilder(DESC_APPLE)
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        assertFalse(DESC_APPLE.equals(editedIngredientDescriptor));

        // different quantity -> returns false
        editedIngredientDescriptor = new IngredientDescriptorBuilder(DESC_APPLE)
                .withQuantity(VALID_QUANTITY_BEEF).build();
        assertFalse(DESC_APPLE.equals(editedIngredientDescriptor));

        // different unit -> returns false
        editedIngredientDescriptor = new IngredientDescriptorBuilder(DESC_APPLE)
                .withUnit(VALID_UNIT_BEEF).build();
        assertFalse(DESC_APPLE.equals(editedIngredientDescriptor));
    }
}
