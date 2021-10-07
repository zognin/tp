package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Ingredient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Ingredient {
    private IngredientName name;
    private Quantity quantity;
    private Unit unit;

    /**
     * Every field must be present and not null.
     */
    public Ingredient(IngredientName ingredientName, Quantity quantity, Unit unit) {
        requireAllNonNull(ingredientName, quantity, unit);
        this.name = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public IngredientName getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }


    /**
     * Returns true if both ingredients have the same ingredient name and units.
     * This defines a weaker notion of equality between two ingredients.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getName().equals(getName())
                && otherIngredient.getUnit().equals(getUnit());
    }


    /**
     * Returns true if both ingredients have the same ingredient name, unit and quantity.
     * This defines a stronger notion of equality between two ingredients.
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
        return otherIngredient.getName().equals(getName())
                && otherIngredient.getQuantity().equals(getQuantity())
                && otherIngredient.getUnit().equals(getUnit());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity, unit);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Quantity: ")
                .append(getQuantity())
                .append("; Unit: ")
                .append(getUnit());

        return builder.toString();
    }
}
