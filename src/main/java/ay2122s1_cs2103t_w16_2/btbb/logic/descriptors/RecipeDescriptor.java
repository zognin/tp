package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Price;

/**
 * Stores the details relevant to a recipe.
 * Some details have to be converted to their model representations before converting to a Recipe model type.
 */
public class RecipeDescriptor {
    private GenericString name;
    private RecipeIngredientList recipeIngredients;
    private Price price;

    public RecipeDescriptor() {}

    /**
     * Constructs an {@code RecipeDescriptor} using the details of an existing {@code RecipeDescriptor}.
     *
     * @param toCopy Existing {@code RecipeDescriptor}.
     */
    public RecipeDescriptor(RecipeDescriptor toCopy) {
        setName(toCopy.name);
        setRecipeIngredients(toCopy.recipeIngredients);
        setPrice(toCopy.price);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, recipeIngredients, price);
    }

    public Optional<GenericString> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(GenericString name) {
        this.name = name;
    }

    public Optional<RecipeIngredientList> getRecipeIngredients() {
        return Optional.ofNullable(recipeIngredients);
    }

    public void setRecipeIngredients(RecipeIngredientList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public Optional<Price> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * Converts a Recipe Descriptor to a Recipe model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Recipe}.
     */
    public Recipe toModelType() {
        requireAllNonNull(name, recipeIngredients, price);
        return new Recipe(name, recipeIngredients, price);
    }

    /**
     * Converts a Recipe Descriptor to a Recipe model type.
     * Missing fields are filled with an existing recipe.
     *
     * @param existingRecipe An existing Recipe that is not null.
     * @return {@code Recipe}.
     */
    public Recipe toModelTypeFrom(Recipe existingRecipe) {
        assert existingRecipe != null;

        GenericString updatedName = getName().orElse(existingRecipe.getName());
        RecipeIngredientList updatedRecipeIngredients =
                getRecipeIngredients().orElse(existingRecipe.getRecipeIngredients());
        Price updatedPrice = getPrice().orElse(existingRecipe.getPrice());

        return new Recipe(updatedName, updatedRecipeIngredients, updatedPrice);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeDescriptor)) {
            return false;
        }

        // state check
        RecipeDescriptor otherRecipeDescriptor = (RecipeDescriptor) other;

        return getName().equals(otherRecipeDescriptor.getName())
                && getRecipeIngredients().equals(otherRecipeDescriptor.getRecipeIngredients())
                && getPrice().equals(otherRecipeDescriptor.getPrice());
    }
}
