package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class RecipeIngredientList {
    public static final String MESSAGE_CONSTRAINTS = "The ingredient list should contain at least one ingredient.\n"
            + "All ingredients should have the format NAME-QUANTITY-UNIT and should be comma separated.\n"
            + "Example: ri/Chicken Eggs-1-whole, Corn-1-whole";
    private final List<Ingredient> ingredients;

    /**
     * Constructs an empty {@code RecipeIngredientList}.
     */
    public RecipeIngredientList() {
        this.ingredients = new ArrayList<>();
    }

    /**
     * Constructs a {@code RecipeIngredientList}.
     *
     * @param ingredients A valid ingredient list.
     */
    public RecipeIngredientList(String ingredients) {
        requireNonNull(ingredients);
        checkArgument(isValidRecipeIngredientList(ingredients), MESSAGE_CONSTRAINTS);
        this.ingredients = RecipeIngredientList.parseRecipeIngredients(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Returns true if a given string is a valid recipe ingredient list.
     *
     * @param test String input to check.
     * @return True if the recipe ingredient list is valid. False otherwise.
     */
    public static boolean isValidRecipeIngredientList(String test) {
        if (test == null) {
            return false;
        }

        List<Ingredient> ingredients = RecipeIngredientList.parseRecipeIngredients(test);
        return ingredients.size() > 0;
    }

    /**
     * Parses the ingredient list to a {@code List<Ingredient>}.
     *
     * @param ingredientList The ingredient list to parse.
     * @return A list of ingredients.
     */
    private static List<Ingredient> parseRecipeIngredients(String ingredientList) {
        requireNonNull(ingredientList);
        List<Ingredient> listOfIngredients = new ArrayList<>();

        String[] ingredientListArray = ingredientList.split(", ");
        for (String individualIngredient : ingredientListArray) {
            Optional<Ingredient> ingredient = parseRecipeIngredient(individualIngredient);
            if (ingredient.isEmpty()) {
                return new ArrayList<>();
            }
            listOfIngredients.add(ingredient.get());
        }

        return listOfIngredients;
    }

    /**
     * Parses the individual ingredient to a {@code Ingredient}.
     *
     * @param individualIngredient The ingredient to parse.
     * @return An {@code Optional<Ingredient>} object.
     */
    private static Optional<Ingredient> parseRecipeIngredient(String individualIngredient) {
        String[] individualIngredientArray = individualIngredient.split("-", 3);

        if (individualIngredientArray.length != 3) {
            return Optional.empty();
        }

        String recipeName = individualIngredientArray[0];
        String quantity = individualIngredientArray[1];
        String unit = individualIngredientArray[2];

        boolean isValidIngredient = GenericString.isValidGenericString(recipeName)
                && Quantity.isValidQuantity(quantity)
                && GenericString.isValidGenericString(unit);

        if (!isValidIngredient) {
            return Optional.empty();
        }

        return Optional.of(new
                Ingredient(new GenericString(recipeName), new Quantity(quantity), new GenericString(unit))
        );
    }

    /**
     * Converts a RecipeIngredientList object into its String representation.
     *
     * @return String representation of a RecipeIngredientList object.
     */
    @Override
    public String toString() {
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
     * Converts a RecipeIngredientList object into its JSON storage String representation.
     *
     * @return JSON String representation of a RecipeIngredientList object.
     */
    public String toJsonStorageString() {
        return ingredients.stream()
                .map(ingredient -> ingredient.getName() + "-" + ingredient.getQuantity() + "-" + ingredient.getUnit())
                .collect(Collectors.joining(", "));
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
