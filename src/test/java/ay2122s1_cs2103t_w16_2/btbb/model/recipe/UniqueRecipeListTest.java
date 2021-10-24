package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_THIRD;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_PASTA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import javafx.collections.ObservableList;

public class UniqueRecipeListTest {
    private final UniqueRecipeList uniqueRecipeList = new UniqueRecipeList();

    @Test
    public void contains_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.contains(null));
    }

    @Test
    public void contains_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.contains(RECIPE_EGG_PRATA));
    }

    @Test
    public void contains_recipeInList_returnsTrue() {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        assertTrue(uniqueRecipeList.contains(RECIPE_EGG_PRATA));
    }

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.add(null));
    }

    @Test
    public void add_validRecipe_listInSortedOrder() {
        uniqueRecipeList.add(RECIPE_LAKSA);
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        ObservableList<Recipe> recipeList = uniqueRecipeList.asUnmodifiableObservableList();
        assertEquals(RECIPE_EGG_PRATA, recipeList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(RECIPE_LAKSA, recipeList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.remove(null));
    }

    @Test
    public void remove_recipeDoesNotExist_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueRecipeList.remove(RECIPE_EGG_PRATA));
    }

    @Test
    public void remove_existingRecipe_removesRecipe() throws NotFoundException {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        uniqueRecipeList.remove(RECIPE_EGG_PRATA);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((UniqueRecipeList) null));
    }

    @Test
    public void setRecipes_list_replacesOwnListWithProvidedList() {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        List<Recipe> recipeList = Collections.singletonList(RECIPE_LAKSA);
        uniqueRecipeList.setRecipes(recipeList);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(RECIPE_LAKSA);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, RECIPE_EGG_PRATA));
    }

    @Test
    public void setRecipe_nullEditedRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(RECIPE_EGG_PRATA, null));
    }

    @Test
    public void setRecipe_invalidTarget_throwsNullPointerException() {
        assertThrows(NotFoundException.class, () -> uniqueRecipeList.setRecipe(RECIPE_EGG_PRATA, RECIPE_PASTA));
    }

    @Test
    public void setRecipe_validTargetAndEditedRecipe_success() throws NotFoundException {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        uniqueRecipeList.setRecipe(RECIPE_EGG_PRATA, RECIPE_PASTA);
        assertFalse(uniqueRecipeList.contains(RECIPE_EGG_PRATA));
        assertTrue(uniqueRecipeList.contains(RECIPE_PASTA));
    }

    @Test
    public void setRecipe_editedRecipeHasDifferentName_listInSortedOrder() throws NotFoundException {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        uniqueRecipeList.add(RECIPE_LAKSA);
        uniqueRecipeList.add(RECIPE_PASTA);
        uniqueRecipeList.setRecipe(RECIPE_PASTA, RECIPE_CHICKEN_RICE);
        ObservableList<Recipe> recipeList = uniqueRecipeList.asUnmodifiableObservableList();
        assertEquals(RECIPE_CHICKEN_RICE, recipeList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(RECIPE_EGG_PRATA, recipeList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(RECIPE_LAKSA, recipeList.get(INDEX_THIRD.getZeroBased()));
    }

    @Test
    public void setRecipes_uniqueRecipesList_listInSortedOrder() {
        uniqueRecipeList.add(RECIPE_EGG_PRATA);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(RECIPE_PASTA);
        expectedUniqueRecipeList.add(RECIPE_LAKSA);
        uniqueRecipeList.setRecipes(expectedUniqueRecipeList);
        ObservableList<Recipe> recipeObservableList = uniqueRecipeList.asUnmodifiableObservableList();
        assertEquals(RECIPE_LAKSA, recipeObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(RECIPE_PASTA, recipeObservableList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void setRecipes_list_listInSortedOrder() {
        List<Recipe> recipeList = List.of(RECIPE_LAKSA, RECIPE_EGG_PRATA, RECIPE_PASTA);
        uniqueRecipeList.setRecipes(recipeList);
        ObservableList<Recipe> recipeObservableList = uniqueRecipeList.asUnmodifiableObservableList();
        assertEquals(RECIPE_EGG_PRATA, recipeObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(RECIPE_LAKSA, recipeObservableList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(RECIPE_PASTA, recipeObservableList.get(INDEX_THIRD.getZeroBased()));
    }
}
