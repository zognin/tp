package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

/**
 * A Model stub that always accepts the recipe being added.
 */
public class ModelStubAcceptingRecipeAdded extends ModelStub {
    private final ArrayList<Recipe> recipesAdded = new ArrayList<>();

    public ArrayList<Recipe> getRecipesAdded() {
        return recipesAdded;
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipesAdded.stream().anyMatch(recipe::isSameRecipe);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        requireNonNull(recipe);
        recipesAdded.add(recipe);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
