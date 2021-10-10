package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
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

    /**
     * Returns the part of command string for the given {@code EditIngredientDescriptor}'s details.
     */
    public static String getEditIngredientDescriptorDetails(IngredientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name ->
                sb.append(PREFIX_INGREDIENT_NAME).append(name).append(" "));
        descriptor.getQuantity().ifPresent(quantity ->
                sb.append(PREFIX_INGREDIENT_QUANTITY).append(quantity).append(" "));
        descriptor.getUnit().ifPresent(unit ->
                sb.append(PREFIX_INGREDIENT_UNIT).append(unit).append(" "));
        return sb.toString();
    }
}
