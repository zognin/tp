package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

/**
 * Finds and lists all orders in the address book whose name, phone number,
 * or address matches the provided parameters.
 */
public class FindOrderCommand extends Command {
    public static final String COMMAND_WORD = "find-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds orders by "
            + "name, phone number or address fields. \n"
            + "Parameters (at least one must be provided): "
            + "[" + PREFIX_CLIENT_NAME + "NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CLIENT_NAME + "alice";

    private PredicateCollection<Order> predicateCollection;

    public FindOrderCommand(PredicateCollection<Order> predicateCollection) {
        this.predicateCollection = predicateCollection;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrderList(predicateCollection);
        return new CommandResult(
                String.format(Messages.MESSAGE_ORDERS_LISTED_OVERVIEW, model.getFilteredOrderList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindOrderCommand)
                && predicateCollection.equals(((FindOrderCommand) other).predicateCollection);
    }
}
