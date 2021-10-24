package ay2122s1_cs2103t_w16_2.btbb.logic.commands.util;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;

/**
 * Contains utility methods used for commands in the various *Command classes.
 */
public class CommandUtil {
    /**
     * Makes a new order with edited ingredients.
     *
     * @param model Model to use in converting the descriptor to a model.
     * @param editedIngredientList Edited ingredient list.
     * @param orderToEdit Original order to edit.
     * @return A new order with edited ingredients while other order fields are the same as the original order.
     * @throws CommandException If there is an invalid command format.
     */
    public static Order makeOrderWithEditedIngredients(
            Model model, RecipeIngredientList editedIngredientList, Order orderToEdit) throws CommandException {
        OrderDescriptor orderDescriptor = new OrderDescriptor();
        orderDescriptor.setRecipeIngredients(editedIngredientList);
        return orderDescriptor.toModelTypeFrom(model, orderToEdit);
    }

    /**
     * Makes a new recipe with edited ingredients.
     *
     * @param editedIngredientList Edited ingredient list.
     * @param recipeToEdit Original recipe to edit.
     * @return A new recipe with edited ingredients while other recipe fields are the same as the original recipe.
     */
    public static Recipe makeRecipeWithEditedIngredients(
            RecipeIngredientList editedIngredientList, Recipe recipeToEdit) {
        RecipeDescriptor recipeDescriptor = new RecipeDescriptor();
        recipeDescriptor.setRecipeIngredients(editedIngredientList);
        return recipeDescriptor.toModelTypeFrom(recipeToEdit);
    }
}
