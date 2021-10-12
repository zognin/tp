package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.Prefix;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;

/**
 * A utility class for Order.
 */
public class OrderUtil {
    /**
     * Adds ingredient to a copy of an ingredient list of an order.
     *
     * @param order Order to get the original ingredient list from.
     * @param ingredient Ingredient to add to the ingredient list.
     * @return New ingredient list.
     */
    public static RecipeIngredientList addIngredientToIngredientList(Order order, Ingredient ingredient) {
        RecipeIngredientList editedRecipeIngredientList = order.getRecipeIngredients();
        List<Ingredient> newIngredients = new ArrayList<>(editedRecipeIngredientList.getIngredients());
        newIngredients.add(ingredient);
        return new RecipeIngredientList(newIngredients);
    }

    /**
     * Removes ingredient from a copy of an ingredient list of an order.
     *
     * @param order Order to get the original ingredient list from.
     * @param ingredient Ingredient to remove from the ingredient list.
     * @return New ingredient list.
     */
    public static RecipeIngredientList removeIngredientFromIngredientList(Order order, Ingredient ingredient) {
        RecipeIngredientList editedRecipeIngredientList = order.getRecipeIngredients();
        List<Ingredient> newIngredients = new ArrayList<>(editedRecipeIngredientList.getIngredients());
        newIngredients.remove(ingredient);
        return new RecipeIngredientList(newIngredients);
    }

    public static String getAddCommand(Order order) {
        return AddOrderCommand.COMMAND_WORD + " " + getOrderDetails(order);
    }

    public static String getOrderDetails(Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append(wrapAroundPrefixAndSpace(PREFIX_CLIENT_NAME, order.getClientName().toString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_CLIENT_PHONE, order.getClientPhone().toString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_CLIENT_ADDRESS, order.getClientAddress().toString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_RECIPE_NAME, order.getRecipeName().toString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_RECIPE_INGREDIENT,
                getUserInputStringForRecipeIngredientList(order.getRecipeIngredients())));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_ORDER_PRICE, order.getPrice().toString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_ORDER_DEADLINE, order.getDeadline().toJsonStorageString()));
        sb.append(wrapAroundPrefixAndSpace(PREFIX_ORDER_QUANTITY, order.getQuantity().toString()));
        return sb.toString();
    }

    private static String wrapAroundPrefixAndSpace(Prefix prefix, String attribute) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(attribute);
        sb.append(" ");
        return sb.toString();
    }

    private static String getUserInputStringForRecipeIngredientList(RecipeIngredientList list) {
        return list.getIngredients().stream()
                .map(ingredient -> ingredient.getName() + "-" + ingredient.getQuantity() + "-" + ingredient.getUnit())
                .collect(Collectors.joining(", "));
    }
}
