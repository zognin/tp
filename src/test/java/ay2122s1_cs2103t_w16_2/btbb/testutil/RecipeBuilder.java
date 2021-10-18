package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class to help build recipes.
 */
public class RecipeBuilder {
    private static final String DEFAULT_NAME = "Egg Prata";
    private static final List<Ingredient> DEFAULT_RECIPE_INGREDIENT_LIST = List.of(
            new Ingredient(new GenericString("Flour"), new Quantity("200"), new GenericString("grams")),
            new Ingredient(new GenericString("Eggs"), new Quantity("1"), new GenericString("whole")));
    private static final String DEFAULT_PRICE = "1.50";

    private GenericString name;
    private RecipeIngredientList recipeIngredients;
    private Price price;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new GenericString(DEFAULT_NAME);
        recipeIngredients = new RecipeIngredientList(DEFAULT_RECIPE_INGREDIENT_LIST);
        price = new Price(DEFAULT_PRICE);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        recipeIngredients = recipeToCopy.getRecipeIngredients();
        price = recipeToCopy.getPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new GenericString(name);
        return this;
    }

    /**
     * Sets the {@code RecipeIngredientList} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withRecipeIngredients(RecipeIngredientList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Recipe build() {
        return new Recipe(name, recipeIngredients, price);
    }
}
