package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UniqueRecipeList implements Iterable<Recipe> {
    private final ObservableList<Recipe> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> internalUnmodifiableList = 
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a recipe to the list.
     *
     * @param toAdd Recipe to be added to the list.
     */
    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent Recipe as the given argument.
     */
    public boolean contains(Recipe toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecipe);
    }

    /**
     * Removes the equivalent recipe from the list of recipes.
     * The recipe must exist in the list.
     *
     * @param toRemove The recipe to remove from the list.
     * @throws NotFoundException when there is no equivalent recipe found in the list.
     */
    public void remove(Recipe toRemove) throws NotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException(Recipe.class.getName());
        }
    }

    /**
     * Replaces the existing target Recipe in the list with an edited Recipe.
     *
     * @param target The target recipe to replace.
     * @param editedRecipe The edited recipe to replace with.
     * @throws NotFoundException If the target recipe does not exist in the list.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) throws NotFoundException {
        requireAllNonNull(target, editedRecipe);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException(Recipe.class.getName());
        }

        internalList.set(index, editedRecipe);
    }

    /**
     * Replaces the contents of this list with {@code recipes}.
     *
     * @param recipes Recipes of the list.
     */
    public void setRecipes(List<Recipe> recipes) {
        requireAllNonNull(recipes);
        internalList.setAll(recipes);
    }

    /**
     * Returns the backing list as an unmodifiable Observable List.
     *
     * @return The full backing recipe list.
     */
    public ObservableList<Recipe> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Recipe> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRecipeList // instanceof handles nulls
                && internalList.equals(((UniqueRecipeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
