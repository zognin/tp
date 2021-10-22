package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand.MESSAGE_DUPLICATE_ORDER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.util.CommandUtil.makeOrderWithEditedIngredients;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
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
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Deletes an ingredient from an existing order in btbb.
 */
public class DeleteOrderIngredientCommand extends Command {
    public static final String COMMAND_WORD = "delete-oi";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ingredient identified by the index number in an order identified by an index number.\n"
            + "Parameters: ORDER_INDEX (must be a positive integer) "
            + PREFIX_INGREDIENT_INDEX + "INGREDIENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INGREDIENT_INDEX + "2";

    public static final String MESSAGE_DELETE_ORDER_INGREDIENT_SUCCESS = "Deleted Order Ingredient: %1$s\n"
            + "Edited Order: %2$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index targetIngredientIndex;
    private final Index targetOrderIndex;

    /**
     * Creates an DeleteOrderIngredientCommand to delete the specified ingredient,
     * identified by its index in the order ingredient list.
     *
     * @param targetIngredientIndex Index of ingredient in the order ingredient list.
     * @param targetOrderIndex Index of the order in the displayed list.
     */
    public DeleteOrderIngredientCommand(Index targetIngredientIndex, Index targetOrderIndex) {
        requireAllNonNull(targetIngredientIndex, targetOrderIndex);

        this.targetIngredientIndex = targetIngredientIndex;
        this.targetOrderIndex = targetOrderIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + DeleteOrderIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        List<Order> lastShownOrderList = model.getFilteredOrderList();

        if (targetOrderIndex.getZeroBased() >= lastShownOrderList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Order orderToEdit = lastShownOrderList.get(targetOrderIndex.getZeroBased());
        Ingredient ingredientToDelete = getIngredientToDelete(orderToEdit);

        RecipeIngredientList editedIngredientList = makeEditedIngredientList(orderToEdit);
        Order editedOrder = makeOrderWithEditedIngredients(model, editedIngredientList, orderToEdit);
        if (!orderToEdit.isSameOrder(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addIngredientQuantity(ingredientToDelete, editedOrder.getQuantity());

        try {
            model.setOrder(orderToEdit, editedOrder);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        return new CommandResult(
                String.format(MESSAGE_DELETE_ORDER_INGREDIENT_SUCCESS, ingredientToDelete, editedOrder), UiTab.HOME);
    }

    private Ingredient getIngredientToDelete(Order orderToEdit) throws CommandException {
        RecipeIngredientList originalRecipeIngredientList = orderToEdit.getRecipeIngredients();

        if (targetIngredientIndex.getZeroBased() >= originalRecipeIngredientList.getIngredients().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        return originalRecipeIngredientList.getIngredients().get(targetIngredientIndex.getZeroBased());
    }

    private RecipeIngredientList makeEditedIngredientList(Order orderToEdit) {
        RecipeIngredientList originalRecipeIngredientList = orderToEdit.getRecipeIngredients();

        List<Ingredient> newIngredients = new ArrayList<>(originalRecipeIngredientList.getIngredients());
        newIngredients.remove(targetIngredientIndex.getZeroBased());

        return new RecipeIngredientList(newIngredients);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteOrderIngredientCommand)) {
            return false;
        }

        // state check
        DeleteOrderIngredientCommand e = (DeleteOrderIngredientCommand) other;
        return targetIngredientIndex.equals(e.targetIngredientIndex)
                && targetOrderIndex.equals(e.targetOrderIndex);
    }
}
