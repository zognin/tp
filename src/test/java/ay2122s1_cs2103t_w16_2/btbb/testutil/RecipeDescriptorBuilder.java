package ay2122s1_cs2103t_w16_2.btbb.testutil;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

public class RecipeDescriptorBuilder {
    private RecipeDescriptor descriptor;

    public RecipeDescriptorBuilder() {
        descriptor = new RecipeDescriptor();
    }

    public RecipeDescriptorBuilder(RecipeDescriptor descriptor) {
        this.descriptor = new RecipeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditRecipeDescriptor} with fields containing {@code recipe}'s details
     */
    public RecipeDescriptorBuilder(Recipe recipe) {
        descriptor = new RecipeDescriptor();
        descriptor.setName(recipe.getName());
        descriptor.setRecipeIngredients(recipe.getRecipeIngredients());
        descriptor.setPrice(recipe.getPrice());
    }

    /**
     * Sets the {@code Name} of the {@code EditRecipeDescriptor} that we are building.
     */
    public RecipeDescriptorBuilder withName(String name) {
        descriptor.setName(new GenericString(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditRecipeDescriptor} that we are building.
     */
    public RecipeDescriptorBuilder withRecipeIngredients(List<Ingredient> recipeIngredients) {
        descriptor.setRecipeIngredients(recipeIngredients != null ? new RecipeIngredientList(recipeIngredients) : null);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditRecipeDescriptor} that we are building.
     */
    public RecipeDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    public RecipeDescriptor build() {
        return descriptor;
    }
}
