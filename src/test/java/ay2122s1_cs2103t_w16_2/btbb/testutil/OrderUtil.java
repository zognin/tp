package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.Prefix;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * A utility class for Order.
 */
public class OrderUtil {
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
                order.getRecipeIngredients().toJsonStorageString()));
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
}
