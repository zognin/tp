package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_COMPLETION_STATUS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Finds and lists all orders in the address book whose client name, client phone number,
 * or client address matches the provided parameters.
 */
public class FindOrderCommand extends Command {
    public static final String COMMAND_WORD = "find-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds orders by "
            + "client name, client phone number, client address, recipe name, order date and order status fields. \n"
            + "Parameters (at least one must be provided): "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "CLIENT_PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS] "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME]"
            + "[" + PREFIX_ORDER_DEADLINE + "ORDER_DATE]" // Note that only deadline date is used
            + "[" + PREFIX_ORDER_COMPLETION_STATUS + "YES_OR_NO]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CLIENT_NAME + "alice";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private PredicateCollection<Order> predicateCollection;

    public FindOrderCommand(PredicateCollection<Order> predicateCollection) {
        this.predicateCollection = predicateCollection;
    }

    @Override
    public CommandResult execute(Model model) {
        logger.info("Executing " + FindOrderCommand.class.getSimpleName());
        requireNonNull(model);
        model.updateFilteredOrderList(predicateCollection);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()),
                UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindOrderCommand)
                && predicateCollection.equals(((FindOrderCommand) other).predicateCollection);
    }
}
