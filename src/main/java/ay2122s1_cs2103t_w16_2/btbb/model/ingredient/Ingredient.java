package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

public class Ingredient {
    private IngredientName ingredientName;
    private Quantity quantity;
    private Unit unit;

    /**
     * Every field must be present and not null.
     */
    public Ingredient(IngredientName ingredientName, Quantity quantity, Unit unit) {
        requireAllNonNull(ingredientName, quantity, unit);
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public IngredientName getIngredientName() {
        return ingredientName;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }


    /**
     * Returns true if both ingredients have the same name and units.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getIngredientName().equals(getIngredientName())
                && otherIngredient.getUnit().equals(getUnit());
    }


    /**
     * Returns true if both ingredients have the same name, unit and quantity.
     * This defines a stronger notion of equality between two clients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient.getIngredientName().equals(getIngredientName())
                && otherIngredient.getQuantity().equals(getQuantity())
                && otherIngredient.getUnit().equals(getUnit());
    }
}
