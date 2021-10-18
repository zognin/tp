package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Marks an order as done in address book.
 */
public class DoneOrderCommand extends Command {
    public static final String COMMAND_WORD = "done-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the order identified by the index number as used in the displayed order list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_DONE_ORDER_SUCCESS = "Order marked as done.\nOrder details: %1$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index targetIndex;

    /**
     * Creates a DoneOrderCommand to mark the specified {@code Order} as done.
     *
     * @param targetIndex Index of Order in currently displayed list to mark as done.
     */
    public DoneOrderCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * Executes this DoneOrderCommand.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Command Result of executing the Done Order Command.
     * @throws CommandException If unable to mark this order as done.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + DoneOrderCommand.class.getSimpleName());

        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToMarkDone = lastShownList.get(targetIndex.getZeroBased());
        Order markedOrder = new Order(orderToMarkDone.getClientName(), orderToMarkDone.getClientPhone(),
                orderToMarkDone.getClientAddress(), orderToMarkDone.getRecipeName(),
                orderToMarkDone.getRecipeIngredients(), orderToMarkDone.getPrice(),
                orderToMarkDone.getDeadline(), orderToMarkDone.getQuantity(), new CompletionStatus(true));

        try {
            model.setOrder(orderToMarkDone, markedOrder);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_DONE_ORDER_SUCCESS,
                orderToMarkDone.toStringWithoutCompletionStatus()), UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneOrderCommand // instanceof handles nulls
                && targetIndex.equals(((DoneOrderCommand) other).targetIndex));
    }
}
