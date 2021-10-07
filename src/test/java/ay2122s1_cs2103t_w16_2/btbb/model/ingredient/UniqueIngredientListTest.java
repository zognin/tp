package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;

public class UniqueIngredientListTest {
    private final UniqueIngredientList uniqueIngredientList = new UniqueIngredientList();

    @Test
    public void contains_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.contains(null));
    }

    @Test
    public void contains_ingredientNotInList_returnsFalse() {
        assertFalse(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        assertTrue(uniqueIngredientList.contains(APPLE));
    }

    @Test
    public void contains_ingredientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIngredientList.add(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withUnit("whole").build();
        assertTrue(uniqueIngredientList.contains(editedApple));
    }

    @Test
    public void add_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.add(null));
    }

    @Test
    public void setIngredients_nullUniqueIngredientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList
                .setIngredients((UniqueIngredientList) null));
    }

    @Test
    public void setIngredients_uniqueIngredientList_replacesOwnListWithProvidedUniqueIngredientList() {
        uniqueIngredientList.add(APPLE);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BEEF);
        uniqueIngredientList.setIngredients(expectedUniqueIngredientList);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void setIngredients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIngredientList.setIngredients((List<Ingredient>) null));
    }

    @Test
    public void setIngredients_list_replacesOwnListWithProvidedList() {
        uniqueIngredientList.add(APPLE);
        List<Ingredient> ingredientList = Collections.singletonList(BEEF);
        uniqueIngredientList.setIngredients(ingredientList);
        UniqueIngredientList expectedUniqueIngredientList = new UniqueIngredientList();
        expectedUniqueIngredientList.add(BEEF);
        assertEquals(expectedUniqueIngredientList, uniqueIngredientList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueIngredientList.asUnmodifiableObservableList().remove(0));
    }
}
