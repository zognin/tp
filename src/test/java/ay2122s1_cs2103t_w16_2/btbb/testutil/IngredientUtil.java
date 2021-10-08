package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

/**
 * A utility class for Ingredient.
 */
public class IngredientUtil {
    /**
     * Returns an add ingredient command string for adding the {@code ingredient}.
     *
     * @param ingredient to add.
     * @return add ingredient command string.
     */
    public static String getAddCommand(Ingredient ingredient) {
        return AddIngredientCommand.COMMAND_WORD + " " + getIngredientDetails(ingredient);
    }

    /**
     * Returns the part of command string for the given {@code ingredient}'s details.
     *
     * @param ingredient to get details from.
     * @return all ingredient details.
     */
    public static String getIngredientDetails(Ingredient ingredient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INGREDIENT_NAME + ingredient.getName().toString() + " ");
        sb.append(PREFIX_INGREDIENT_QUANTITY + ingredient.getQuantity().toString() + " ");
        sb.append(PREFIX_INGREDIENT_UNIT + ingredient.getUnit().toString() + " ");
        return sb.toString();
    }
}
