package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;

public class IngredientDescriptorTest {
    @Test
    public void toModelType_nullFields_throwsNullPointerException() {
        // All fields are null
        assertThrows(NullPointerException.class, () -> new IngredientDescriptor().toModelType());

        // Name field is null
        IngredientDescriptor nullNameDescriptor = new IngredientDescriptorBuilder().build();
        nullNameDescriptor.setName(null);
        assertThrows(NullPointerException.class, nullNameDescriptor::toModelType);

        // Quantity field is null
        IngredientDescriptor nullQuantityDescriptor = new IngredientDescriptorBuilder().build();
        nullQuantityDescriptor.setQuantity(null);
        assertThrows(NullPointerException.class, nullQuantityDescriptor::toModelType);

        // Unit field is null
        IngredientDescriptor nullUnitDescriptor = new IngredientDescriptorBuilder().build();
        nullUnitDescriptor.setUnit(null);
        assertThrows(NullPointerException.class, nullUnitDescriptor::toModelType);
    }

    @Test
    public void toModelType_validDescriptor_success() {
        Ingredient expectedModelIngredient = new IngredientBuilder().build();
        IngredientDescriptor validDescriptor = new IngredientDescriptorBuilder(expectedModelIngredient).build();
        assertEquals(expectedModelIngredient, validDescriptor.toModelType());
    }

    @Test
    public void toModelTypeFrom() {
        Ingredient expectedModelIngredient = new IngredientBuilder().build();

        // All fields in descriptor are non null
        Ingredient anotherModelIngredient = new IngredientBuilder().withIngredientName("Imposter").build();
        IngredientDescriptor validDescriptor = new IngredientDescriptorBuilder(expectedModelIngredient).build();
        assertEquals(expectedModelIngredient, validDescriptor.toModelTypeFrom(anotherModelIngredient));

        // Name field in descriptor is null
        validDescriptor = new IngredientDescriptorBuilder(expectedModelIngredient).build();
        validDescriptor.setName(null);
        assertEquals(expectedModelIngredient, validDescriptor.toModelTypeFrom(expectedModelIngredient));

        // Quantity field in descriptor is null
        validDescriptor = new IngredientDescriptorBuilder(expectedModelIngredient).build();
        validDescriptor.setQuantity(null);
        assertEquals(expectedModelIngredient, validDescriptor.toModelTypeFrom(expectedModelIngredient));

        // Unit field in descriptor is null
        validDescriptor = new IngredientDescriptorBuilder(expectedModelIngredient).build();
        validDescriptor.setUnit(null);
        assertEquals(expectedModelIngredient, validDescriptor.toModelTypeFrom(expectedModelIngredient));
    }

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
