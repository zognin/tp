package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

public class TypicalRecipes {
    public static final RecipeIngredientList INGREDIENT_LIST_EGG_PRATA = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Egg").withQuantity("2").withUnit("whole").build(),
            new IngredientBuilder().withIngredientName("Flour").withQuantity("100").withUnit("g").build()
    ));
    public static final RecipeIngredientList INGREDIENT_LIST_CHICKEN_RICE = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Chicken").withQuantity("5").withUnit("cuts").build(),
            new IngredientBuilder().withIngredientName("Rice").withQuantity("100").withUnit("g").build()
    ));
    public static final RecipeIngredientList INGREDIENT_LIST_LAKSA = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Egg").withQuantity("1").withUnit("whole").build(),
            new IngredientBuilder().withIngredientName("Noodles").withQuantity("100").withUnit("g").build()
    ));
    public static final RecipeIngredientList INGREDIENT_LIST_PASTA = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Egg").withQuantity("1").withUnit("whole").build(),
            new IngredientBuilder().withIngredientName("Noodles").withQuantity("100").withUnit("g").build(),
            new IngredientBuilder().withIngredientName("Tomato Sauce").withQuantity("10").withUnit("g").build()
    ));
    public static final RecipeIngredientList INGREDIENT_LIST_MEE_REBUS = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Egg").withQuantity("2").withUnit("whole").build(),
            new IngredientBuilder().withIngredientName("Noodles").withQuantity("100").withUnit("g").build(),
            new IngredientBuilder().withIngredientName("Curry Powder").withQuantity("10").withUnit("g").build()
    ));
    public static final RecipeIngredientList INGREDIENT_LIST_MEE_GORENG = new RecipeIngredientList(List.of(
            new IngredientBuilder().withIngredientName("Egg").withQuantity("2").withUnit("whole").build(),
            new IngredientBuilder().withIngredientName("Noodles").withQuantity("100").withUnit("g").build(),
            new IngredientBuilder().withIngredientName("Chilli Powder").withQuantity("10").withUnit("g").build()
    ));

    public static final Recipe RECIPE_EGG_PRATA = new RecipeBuilder().withName("Egg Prata")
            .withRecipeIngredients(INGREDIENT_LIST_EGG_PRATA).withPrice("2").build();
    public static final Recipe RECIPE_CHICKEN_RICE = new RecipeBuilder().withName("Chicken Rice")
            .withRecipeIngredients(INGREDIENT_LIST_CHICKEN_RICE).withPrice("3").build();
    public static final Recipe RECIPE_LAKSA = new RecipeBuilder().withName("Laksa")
            .withRecipeIngredients(INGREDIENT_LIST_LAKSA).withPrice("4").build();
    public static final Recipe RECIPE_PASTA = new RecipeBuilder().withName("Pasta")
            .withRecipeIngredients(INGREDIENT_LIST_PASTA).withPrice("4").build();
    public static final Recipe RECIPE_MEE_REBUS = new RecipeBuilder().withName("Mee Rebus")
            .withRecipeIngredients(INGREDIENT_LIST_MEE_REBUS).withPrice("3").build();
    public static final Recipe RECIPE_MEE_GORENG = new RecipeBuilder().withName("Mee Goreng")
            .withRecipeIngredients(INGREDIENT_LIST_MEE_GORENG).withPrice("4").build();

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }
        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(RECIPE_EGG_PRATA, RECIPE_CHICKEN_RICE, RECIPE_LAKSA, RECIPE_PASTA,
                RECIPE_MEE_REBUS, RECIPE_MEE_GORENG));
    }
}
