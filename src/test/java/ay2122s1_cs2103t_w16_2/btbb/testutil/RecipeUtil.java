package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

/**
 * A utility class for Recipe.
 */
public class RecipeUtil {
    /**
     * Returns an add recipe command string for adding the {@code recipe}.
     *
     * @param recipe Recipe to make the command with.
     * @return An add recipe command string.
     */
    public static String getAddCommand(Recipe recipe) {
        return AddRecipeCommand.COMMAND_WORD + " " + getRecipeDetails(recipe);
    }

    /**
     * Returns the part of command string for the given {@code recipe}'s details.
     *
     * @param recipe Recipe to get details from.
     * @return Part of the add recipe command string.
     */
    public static String getRecipeDetails(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_RECIPE_NAME + recipe.getName().toString() + " ");
        if (recipe.getRecipeIngredients().getIngredients().size() > 0) {
            sb.append(PREFIX_RECIPE_INGREDIENT + recipe.getRecipeIngredients().toString() + " ");
        }
        sb.append(PREFIX_RECIPE_PRICE + recipe.getPrice().toString() + " ");
        return sb.toString();
    }
}
