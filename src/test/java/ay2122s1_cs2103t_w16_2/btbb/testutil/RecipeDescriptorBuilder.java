package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Price;

public class RecipeDescriptorBuilder {
    private RecipeDescriptor descriptor;

    /**
     * Constructs a RecipeDescriptorBuilder with no values set for {@code RecipeDescriptor}.
     */
    public RecipeDescriptorBuilder() {
        descriptor = new RecipeDescriptor();
    }

    /**
     * Constructs a RecipeDescriptorBuilder with a copy of the given {@code RecipeDescriptor}.
     *
     * @param descriptor The RecipeDescriptor to copy.
     */
    public RecipeDescriptorBuilder(RecipeDescriptor descriptor) {
        this.descriptor = new RecipeDescriptor(descriptor);
    }

    /**
     * Constructs a RecipeDescriptorBuilder with a {@code RecipeDescriptor} whose values are set to the
     * field values of the given {@code Recipe}
     *
     * @param recipe The recipe whose values are to be set on the {@code RecipeDescriptor}.
     */
    public RecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new RecipeDescriptor();
        descriptor.setName(recipe.getName());
        descriptor.setRecipeIngredients(recipe.getRecipeIngredients());
        descriptor.setPrice(recipe.getPrice());
    }

    /**
     * Sets the name of the {@code RecipeDescriptor} we are building.
     *
     * @param name The name to set for the {@code RecipeDescriptor}.
     * @return A RecipeDescriptorBuilder object with the name set.
     */
    public RecipeDescriptorBuilder withName(String name) {
        descriptor.setName(new GenericString(name));
        return this;
    }

    /**
     * Sets the ingredients list of the {@code RecipeDescriptor} that we are building.
     *
     * @param recipeIngredients The list of ingredients to set for the {@code RecipeDescriptor}.
     * @return A RecipeDescriptorBuilder object with the ingredients set.
     */
    public RecipeDescriptorBuilder withRecipeIngredients(List<Ingredient> recipeIngredients) {
        descriptor.setRecipeIngredients(recipeIngredients == null ? null : new RecipeIngredientList(recipeIngredients));
        return this;
    }

    /**
     * Sets the price of the {@code RecipeDescriptor} we are building.
     *
     * @param price The price to set for the {@code RecipeDescriptor}.
     * @return A RecipeDescriptorBuilder object with the price set.
     */
    public RecipeDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Returns the {@code RecipeDescriptor} that has been built.
     *
     * @return The RecipeDescriptor object that has been built.
     */
    public RecipeDescriptor build() {
        return descriptor;
    }
}
