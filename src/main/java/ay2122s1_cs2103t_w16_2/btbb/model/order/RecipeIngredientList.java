package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

public class RecipeIngredientList {
    public static final String MESSAGE_CONSTRAINTS = "The ingredient list should contain at least one ingredient if "
            + PREFIX_RECIPE_INGREDIENT + " is provided.\n"
            + "All ingredients should have the format NAME-QUANTITY-UNIT and should be comma separated.\n"
            + "Example: ri/Chicken Eggs-1-whole, Corn-1-whole";
    private final List<Ingredient> ingredients;

    /**
     * Constructs a {@code RecipeIngredientList}.
     *
     * @param ingredients A valid ingredient list.
     */
    public RecipeIngredientList(List<Ingredient> ingredients) {
        requireNonNull(ingredients);
        checkArgument(isValidInternalRecipeIngredientList(ingredients), MESSAGE_CONSTRAINTS);
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Returns true if a given string is a valid recipe ingredient list.
     *
     * @param test List to check.
     * @return True if the recipe ingredient list is valid. False otherwise.
     */
    public static boolean isValidRecipeIngredientList(List<Ingredient> test) {
        return test != null && test.size() > 0;
    }

    /**
     * Returns true if a given string is a valid recipe ingredient list.
     * For internal use where there is a wider definition of a valid recipe ingredient list.
     *
     * @param test List to check.
     * @return True if the recipe ingredient list is valid. False otherwise.
     */
    public static boolean isValidInternalRecipeIngredientList(List<Ingredient> test) {
        return test != null;
    }

    /**
     * Converts a RecipeIngredientList object into its String representation.
     *
     * @return String representation of a RecipeIngredientList object.
     */
    @Override
    public String toString() {
        if (ingredients.size() == 0) {
            return "This order's ingredient list is empty.";
        }
        return ingredients.stream().map(Ingredient::toString).collect(Collectors.joining(", "));
    }

    /**
     * Converts a RecipeIngredientList object into its String representation that will be used in the UI.
     *
     * @return UI String representation of a RecipeIngredientList object.
     */
    public String toDisplayString() {
        if (ingredients.size() == 0) {
            return "This order's ingredient list is empty.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            stringBuilder
                    .append(i + 1)
                    .append(". ")
                    .append(ingredients.get(i))
                    .append(i < ingredients.size() - 1 ? "\n" : "");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns true if object and this RecipeIngredientList are the same.
     *
     * @param other An object to compare this RecipeIngredientList to.
     * @return True if they are the same. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecipeIngredientList)) {
            return false;
        }

        RecipeIngredientList otherRecipeIngredientList = (RecipeIngredientList) other;
        return ingredients.containsAll(otherRecipeIngredientList.ingredients)
                && otherRecipeIngredientList.ingredients.containsAll(ingredients);
    }

    /**
     * Implements hashcode for RecipeIngredientList objects.
     *
     * @return The hashcode for the RecipeIngredientList object.
     */
    @Override
    public int hashCode() {
        return ingredients.hashCode();
    }
}
