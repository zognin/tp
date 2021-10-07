package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

public class ModelStubAcceptingIngredientAdded extends ModelStub {
    private final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();

    public ArrayList<Ingredient> getIngredientsAdded() {
        return ingredientsAdded;
    }

    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredientsAdded.stream().anyMatch(ingredient::isSameIngredient);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        ingredientsAdded.add(ingredient);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
