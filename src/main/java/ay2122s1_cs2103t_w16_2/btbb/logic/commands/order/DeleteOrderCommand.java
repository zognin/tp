package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Deletes an order identified using it's displayed index from the address book.
 */
public class DeleteOrderCommand extends Command {
    public static final String COMMAND_WORD = "delete-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the order identified by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORDER_SUCCESS = "Deleted Order: %1$s";

    private final Index targetIndex;

    public DeleteOrderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (!orderToDelete.getCompletionStatus().getIsFinished()) {
            orderToDelete.getRecipeIngredients().getIngredients().forEach(ingredient -> {
                model.addIngredientQuantity(ingredient, orderToDelete.getQuantity());
            });
        }

        try {
            model.deleteOrder(orderToDelete);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ORDER_SUCCESS, orderToDelete), UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrderCommand) other).targetIndex)); // state check
    }
}
