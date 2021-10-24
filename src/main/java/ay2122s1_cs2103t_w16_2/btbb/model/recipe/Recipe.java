package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Represents a Recipe in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe implements Comparable<Recipe> {
    private final GenericString name;
    private final RecipeIngredientList recipeIngredients;
    private final RecipePrice recipePrice;

    /**
     * Constructs a recipe object with the given fields.
     *
     * @param name The name of the recipe.
     * @param recipeIngredients The ingredients of the recipe.
     * @param recipePrice The price of the recipe.
     */
    public Recipe(GenericString name, RecipeIngredientList recipeIngredients, RecipePrice recipePrice) {
        requireAllNonNull(name, recipeIngredients, recipePrice);
        this.name = name;
        this.recipeIngredients = recipeIngredients;
        this.recipePrice = recipePrice;
    }

    public GenericString getName() {
        return name;
    }

    public RecipeIngredientList getRecipeIngredients() {
        return recipeIngredients;
    }

    public RecipePrice getRecipePrice() {
        return recipePrice;
    }

    /**
     * Returns true if both recipes have the same name, ingredients and price.
     *
     * @param otherRecipe The recipe to compare this recipe against.
     * @return true if both recipes have the same name, ingredients and price. Returns false otherwise.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && getName().isSameGenericString(otherRecipe.getName())
                && getRecipePrice().equals(otherRecipe.getRecipePrice())
                && getRecipeIngredients().equals(otherRecipe.getRecipeIngredients());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return getName().equals(otherRecipe.getName())
                && getRecipeIngredients().equals(otherRecipe.getRecipeIngredients())
                && getRecipePrice().equals(otherRecipe.getRecipePrice());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Ingredients: ")
                .append(getRecipeIngredients())
                .append("; Price: ")
                .append(getRecipePrice());

        return builder.toString();
    }

    @Override
    public int compareTo(Recipe other) {
        return name.compareTo(other.getName());
    }
}
