package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

/**
 * A Model stub that contains a single recipe.
 */
public class ModelStubWithRecipe extends ModelStub {
    private final Recipe recipe;

    /**
     * Constructs a ModelStubWithRecipe object which contains the given recipe.
     *
     * @param recipe The recipe that this model should contain.
     */
    public ModelStubWithRecipe(Recipe recipe) {
        requireNonNull(recipe);
        this.recipe = recipe;
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return this.recipe.isSameRecipe(recipe);
    }
}
