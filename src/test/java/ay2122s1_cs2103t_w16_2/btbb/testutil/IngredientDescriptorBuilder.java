package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;

public class IngredientDescriptorBuilder {
    private IngredientDescriptor descriptor;

    public IngredientDescriptorBuilder() {
        descriptor = new IngredientDescriptor();
    }

    public IngredientDescriptorBuilder(IngredientDescriptor descriptor) {
        this.descriptor = new IngredientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditIngredientDescriptor} with fields containing {@code ingredient}'s details
     */
    public IngredientDescriptorBuilder(Ingredient ingredient) {
        descriptor = new IngredientDescriptor();
        descriptor.setIngredientName(ingredient.getIngredientName());
        descriptor.setQuantity(ingredient.getQuantity());
        descriptor.setUnit(ingredient.getUnit());
    }

    /**
     * Sets the {@code IngredientName} of the {@code EditIngredientDescriptor} that we are building.
     */
    public IngredientDescriptorBuilder withIngredientName(String ingredientName) {
        descriptor.setIngredientName(new IngredientName(ingredientName));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditIngredientDescriptor} that we are building.
     */
    public IngredientDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code IngredientName} of the {@code EditIngredientDescriptor} that we are building.
     */
    public IngredientDescriptorBuilder withUnit(String unit) {
        descriptor.setUnit(new Unit(unit));
        return this;
    }

    public IngredientDescriptor build() {
        return descriptor;
    }

}
