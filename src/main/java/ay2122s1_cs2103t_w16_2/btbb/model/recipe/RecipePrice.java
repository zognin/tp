package ay2122s1_cs2103t_w16_2.btbb.model.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.math.BigDecimal;

import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Represents the recipe price of a recipe in BTBB.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class RecipePrice {
    public static final String MESSAGE_CONSTRAINTS =
            "Recipe price should be positive and less than $2500.00. It should contain up to 4 digits, with an "
                    + "optional 2 decimal places";
    public static final String VALIDATION_REGEX = "^\\d{1,4}(\\.\\d{2})?$";
    public static final BigDecimal MAX_RECIPE_PRICE = new BigDecimal(2500.00);
    public static final BigDecimal MIN_RECIPE_PRICE = new BigDecimal(0);
    private final BigDecimal recipePrice;

    /**
     * Constructs a {@code RecipePrice}.
     *
     * @param recipePrice A valid recipe price.
     */
    public RecipePrice(String recipePrice) {
        requireNonNull(recipePrice);
        checkArgument(isValidRecipePrice(recipePrice), MESSAGE_CONSTRAINTS);
        this.recipePrice = new BigDecimal(recipePrice);
    }

    /**
     * Returns true if a given string is a valid recipe price.
     *
     * @param test String input to check.
     * @return True if the recipe price is valid. False otherwise.
     */
    public static boolean isValidRecipePrice(String test) {
        if (test == null || !test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            BigDecimal recipePrice = new BigDecimal(test);
            return recipePrice.compareTo(MAX_RECIPE_PRICE) < 0 && recipePrice.compareTo(MIN_RECIPE_PRICE) > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    public OrderPrice multiplyRecipePriceByQuantity(Quantity quantity) {
        return new OrderPrice(recipePrice.multiply(new BigDecimal(quantity.getQuantityAsInt())).toString());
    }

    /**
     * Converts a RecipePrice object into its String representation.
     *
     * @return String representation of a recipe price object.
     */
    @Override
    public String toString() {
        return String.format("%.2f", recipePrice);
    }

    /**
     * Returns true if object and this recipe price are the same.
     *
     * @param other An object to compare this recipe price to.
     * @return True if they are the same. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipePrice // instanceof handles nulls
                && (recipePrice.compareTo(((RecipePrice) other).recipePrice) == 0)); // state check
    }

    /**
     * Implements hashcode for recipe price objects.
     *
     * @return The hashcode for the recipe price object.
     */
    @Override
    public int hashCode() {
        return recipePrice.hashCode();
    }
}
