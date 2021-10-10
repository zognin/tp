package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds an order to the address book.
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the address book.\n"
            + "Parameters: "
            + "[" + PREFIX_CLIENT_INDEX + "CLIENT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "CLIENT_PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS]\n"
            + "Additional Info: If CLIENT_INDEX is not present, CLIENT_NAME, CLIENT_PHONE and CLIENT_ADDRESS must be"
            + " present";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the address book";

    private final OrderDescriptor orderDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Order}
     */
    public AddOrderCommand(OrderDescriptor orderDescriptor) {
        requireNonNull(orderDescriptor);
        this.orderDescriptor = orderDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Order order = orderDescriptor.toModelType(model);

        if (model.hasOrder(order)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addOrder(order);
        return new CommandResult(String.format(MESSAGE_SUCCESS, order), UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && orderDescriptor.equals(((AddOrderCommand) other).orderDescriptor));
    }
}
