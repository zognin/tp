package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

public class RecipeUtil {
    public static String getAddCommand(Recipe recipe) {
        return AddRecipeCommand.COMMAND_WORD + " " + getRecipeDetails(recipe);
    }

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
