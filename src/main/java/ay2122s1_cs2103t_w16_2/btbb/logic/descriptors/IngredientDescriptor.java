package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Stores the details to relevant to a ingredient.
 * Some details have to be converted to their model representations before converting to a Ingredient model type.
 */
public class IngredientDescriptor {
    private GenericString name;
    private Quantity quantity;
    private GenericString unit;

    public IngredientDescriptor() {}

    /**
     * Copy constructor.
     */
    public IngredientDescriptor(IngredientDescriptor toCopy) {
        setName(toCopy.name);
        setQuantity(toCopy.quantity);
        setUnit(toCopy.unit);
    }

    /**
     * Sets the name of this ingredient descriptor.
     *
     * @param name to change to.
     */
    public void setName(GenericString name) {
        this.name = name;
    }

    /**
     * Gets the name of this ingredient descriptor.
     *
     * @return name of ingredient, if it is not null.
     */
    public Optional<GenericString> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Sets the quantity of this ingredient descriptor.
     *
     * @param quantity to change to.
     */
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of this ingredient descriptor.
     *
     * @return quantity of ingredient, if it is not null.
     */
    public Optional<Quantity> getQuantity() {
        return Optional.ofNullable(quantity);
    }

    /**
     * Sets the unit of this ingredient descriptor.
     *
     * @param unit to change to.
     */
    public void setUnit(GenericString unit) {
        this.unit = unit;
    }

    /**
     * Gets the unit of this ingredient descriptor.
     *
     * @return unit of ingredient, if it is not null.
     */
    public Optional<GenericString> getUnit() {
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
