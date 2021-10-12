package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class IngredientBuilder {
    private static final String DEFAULT_INGREDIENT_NAME = "Cauliflower";
    private static final String DEFAULT_QUANTITY = "14";
    private static final String DEFAULT_UNIT = "whole";

    private GenericString ingredientName;
    private Quantity quantity;
    private GenericString unit;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public IngredientBuilder() {
        ingredientName = new GenericString(DEFAULT_INGREDIENT_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        unit = new GenericString(DEFAULT_UNIT);
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public IngredientBuilder(Ingredient ingredientToCopy) {
        ingredientName = ingredientToCopy.getName();
        quantity = ingredientToCopy.getQuantity();
        unit = ingredientToCopy.getUnit();
    }

    /**
     * Sets the {@code ingredientName} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withIngredientName(String ingredientName) {
        this.ingredientName = new GenericString(ingredientName);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Unit} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withUnit(String unit) {
        this.unit = new GenericString(unit);
        return this;
    }

    public Ingredient build() {
        return new Ingredient(ingredientName, quantity, unit);
    }
}
