package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;

public class IngredientBuilder {
    private static final String DEFAULT_INGREDIENT_NAME = "Cauliflower";
    private static final String DEFAULT_QUANTITY = "14";
    private static final String DEFAULT_UNIT = "whole";

    private IngredientName ingredientName;
    private Quantity quantity;
    private Unit unit;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public IngredientBuilder() {
        ingredientName = new IngredientName(DEFAULT_INGREDIENT_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        unit = new Unit(DEFAULT_UNIT);
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public IngredientBuilder(Ingredient ingredientToCopy) {
        ingredientName = ingredientToCopy.getIngredientName();
        quantity = ingredientToCopy.getQuantity();
        unit = ingredientToCopy.getUnit();
    }

    /**
     * Sets the {@code IngredientName} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withIngredientName(String ingredientName) {
        this.ingredientName = new IngredientName(ingredientName);
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
        this.unit = new Unit(unit);
        return this;
    }

    public Ingredient build() {
        return new Ingredient(ingredientName, quantity, unit);
    }
}
