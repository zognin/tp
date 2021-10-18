package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

public class Recipe {
    private final GenericString name;
    private final RecipeIngredientList recipeIngredients;
    private final Price price;

    public Recipe(GenericString name, RecipeIngredientList recipeIngredients, Price price) {
        requireAllNonNull(name, recipeIngredients, price);
        this.name = name;
        this.recipeIngredients = recipeIngredients;
        this.price = price;
    }

    public GenericString getName() {
        return name;
    }

    public RecipeIngredientList getRecipeIngredients() {
        return recipeIngredients;
    }

    public Price getPrice() {
        return price;
    }

    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && getName().equals(otherRecipe.getName())
                && getPrice().equals(otherRecipe.getPrice())
                && getRecipeIngredients().equals(otherRecipe.getRecipeIngredients());
    }

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
                && getPrice().equals(otherRecipe.getPrice());
    }
}
