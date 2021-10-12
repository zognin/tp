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
import ay2122s1_cs2103t_w16_2.btbb.model.order.IsDone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class UndoneOrderCommand extends Command {
    public static final String COMMAND_WORD = "undone-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the order identified by the index number as used in the displayed order list as undone.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_UNDONE_ORDER_SUCCESS = "Order marked as undone.\nOrder details: %1$s";

    private final Index targetIndex;

    /**
     * Creates a UndoneOrderCommand to mark the specified {@code Order} as undone.
     *
     * @param targetIndex Index of Order in currently displayed list to mark as undone.
     */
    public UndoneOrderCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToMarkUndone = lastShownList.get(targetIndex.getZeroBased());
        Order markedOrder = new Order(orderToMarkUndone.getClientName(), orderToMarkUndone.getClientPhone(),
                orderToMarkUndone.getClientAddress(), orderToMarkUndone.getRecipeName(),
                orderToMarkUndone.getRecipeIngredients(), orderToMarkUndone.getPrice(),
                orderToMarkUndone.getDeadline(), orderToMarkUndone.getQuantity(), new IsDone(false));

        try {
            model.setOrder(orderToMarkUndone, markedOrder);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_UNDONE_ORDER_SUCCESS, orderToMarkUndone.toStringWithoutIsDone()),
                UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoneOrderCommand // instanceof handles nulls
                && targetIndex.equals(((UndoneOrderCommand) other).targetIndex));
    }
}
