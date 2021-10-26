package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_ORDER;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_ORDERS;
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
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Edits the details of an existing order in btbb.
 */
public class EditOrderCommand extends Command {
    public static final String COMMAND_WORD = "edit-o";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the order identified "
            + "by the index number used in the displayed order list.\n"
            + "\t    Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLIENT_INDEX + "CLIENT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "CLIENT_PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS]\n\t\t" + "      "
            + "[" + PREFIX_RECIPE_INDEX + "RECIPE_INDEX (must be a positive integer)] "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME] "
            + "[" + PREFIX_ORDER_PRICE + "ORDER_PRICE] "
            + "[" + PREFIX_ORDER_DEADLINE + "ORDER_DEADLINE] "
            + "[" + PREFIX_ORDER_QUANTITY + "ORDER_QUANTITY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_RECIPE_NAME + "Chicken Rice "
            + PREFIX_ORDER_PRICE + "4.50";

    public static final String MESSAGE_EDIT_ORDER_SUCCESS = "Edited Order: %1$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index index;
    private final OrderDescriptor editOrderDescriptor;

    /**
     * Constructs a EditOrderCommand object.
     *
     * @param index The index of the order in the filtered order list.
     * @param editOrderDescriptor Contains details to edit the order with.
     */
    public EditOrderCommand(Index index, OrderDescriptor editOrderDescriptor) {
        requireAllNonNull(index, editOrderDescriptor);

        this.index = index;
        this.editOrderDescriptor = new OrderDescriptor(editOrderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + EditOrderCommand.class.getSimpleName());

        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownList.get(index.getZeroBased());
        Order editedOrder = editOrderDescriptor.toModelTypeFrom(model, orderToEdit);

        if (!orderToEdit.isSameOrder(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        int compareQuantityResult = editedOrder.getQuantity().compareTo(orderToEdit.getQuantity());
        if (compareQuantityResult < 0) {
            // editedOrder has quantity < orderToEdit
            orderToEdit.getRecipeIngredients().getIngredients().forEach(ingredient -> {
                model.addIngredientQuantity(ingredient,
                        orderToEdit.getQuantity().minusQuantityBy(editedOrder.getQuantity(), new Quantity("1")));
            });
        } else if (compareQuantityResult > 0) {
            // editedOrder has quantity > orderToEdit
            orderToEdit.getRecipeIngredients().getIngredients().forEach(ingredient -> {
                model.minusIngredientQuantity(ingredient,
                        editedOrder.getQuantity().minusQuantityBy(orderToEdit.getQuantity(), new Quantity("1")));
            });
        }

        try {
            model.setOrder(orderToEdit, editedOrder);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        model.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder), UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditOrderCommand)) {
            return false;
        }

        // state check
        EditOrderCommand e = (EditOrderCommand) other;
        return index.equals(e.index) && editOrderDescriptor.equals(e.editOrderDescriptor);
    }
}
