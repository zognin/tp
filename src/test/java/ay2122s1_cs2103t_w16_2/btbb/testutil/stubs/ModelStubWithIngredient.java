package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

public class ModelStubWithIngredient extends ModelStub {
    private final Ingredient ingredient;

    /**
     * Constructs a ModelStubWithIngredient object which contains the given ingredient.
     *
     * @param ingredient The ingredient that this model should contain.
     */
    public ModelStubWithIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        this.ingredient = ingredient;
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return this.ingredient.isSameIngredient(ingredient);
    }
}
