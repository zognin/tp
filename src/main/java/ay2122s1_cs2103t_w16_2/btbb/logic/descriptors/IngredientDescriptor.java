package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;

import java.util.Optional;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

public class IngredientDescriptor {
    private IngredientName ingredientName;
    private Quantity quantity;
    private Unit unit;

    public IngredientDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public IngredientDescriptor(IngredientDescriptor toCopy) {
        setIngredientName(toCopy.ingredientName);
        setQuantity(toCopy.quantity);
        setUnit(toCopy.unit);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(ingredientName, quantity, unit);
    }

    public void setIngredientName(IngredientName name) {
        this.ingredientName = name;
    }

    public Optional<IngredientName> getIngredientName() {
        return Optional.ofNullable(ingredientName);
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Optional<Quantity> getQuantity() {
        return Optional.ofNullable(quantity);
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Optional<Unit> getUnit() {
        return Optional.ofNullable(unit);
    }

    /**
     * Converts a Ingredient Descriptor to a Ingredient model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Ingredient}.
     */
    public Ingredient toModelType() {
        requireAllNonNull(ingredientName, quantity, unit);
        return new Ingredient(ingredientName, quantity, unit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IngredientDescriptor)) {
            return false;
        }

        // state check
        IngredientDescriptor e = (IngredientDescriptor) other;

        return getIngredientName().equals(e.getIngredientName())
                && getQuantity().equals(e.getQuantity())
                && getUnit().equals(e.getUnit());
    }
}
