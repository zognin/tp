package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;

public class IngredientDescriptor {
    private IngredientName name;
    private Quantity quantity;
    private Unit unit;

    public IngredientDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public IngredientDescriptor(IngredientDescriptor toCopy) {
        setName(toCopy.name);
        setQuantity(toCopy.quantity);
        setUnit(toCopy.unit);
    }

    public void setName(IngredientName name) {
        this.name = name;
    }

    public Optional<IngredientName> getName() {
        return Optional.ofNullable(name);
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
        requireAllNonNull(name, quantity, unit);
        return new Ingredient(name, quantity, unit);
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

        return getName().equals(e.getName())
                && getQuantity().equals(e.getQuantity())
                && getUnit().equals(e.getUnit());
    }
}
