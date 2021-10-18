package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand.MESSAGE_DUPLICATE_ORDER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.util.CommandUtil.makeOrderWithEditedIngredients;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
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
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds an ingredient to an existing order in btbb.
 */
public class AddOrderIngredientCommand extends Command {
    public static final String COMMAND_WORD = "add-oi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the order identified "
            + "by the index number used in the displayed order list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INGREDIENT_NAME + "NAME "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY "
            + PREFIX_INGREDIENT_UNIT + "UNIT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INGREDIENT_NAME + "Rice "
            + PREFIX_INGREDIENT_QUANTITY + "400 "
            + PREFIX_INGREDIENT_UNIT + "g";

    public static final String MESSAGE_ADD_ORDER_INGREDIENT_SUCCESS = "Added Order Ingredient: %1$s\n"
            + "Edited Order: %2$s";
    public static final String MESSAGE_DUPLICATE_ORDER_INGREDIENT = "This ingredient already exists in the order";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index index;
    private final IngredientDescriptor ingredientDescriptor;

    /**
     * Creates an AddOrderIngredientCommand to add the specified {@code Ingredient}.
     *
     * @param index of the order in the filtered order list.
     * @param ingredientDescriptor of ingredient to add to the order.
     */
    public AddOrderIngredientCommand(Index index, IngredientDescriptor ingredientDescriptor) {
        requireAllNonNull(index, ingredientDescriptor);

        this.index = index;
        this.ingredientDescriptor = ingredientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + AddOrderIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        List<Order> lastShownList = model.getFilteredOrderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        Ingredient ingredientToAdd = ingredientDescriptor.toModelType();
        Order orderToEdit = lastShownList.get(index.getZeroBased());

        RecipeIngredientList editedIngredientList = makeEditedIngredientList(ingredientToAdd, orderToEdit);
        Order editedOrder = makeOrderWithEditedIngredients(model, editedIngredientList, orderToEdit);
        if (!orderToEdit.isSameOrder(editedOrder) && model.hasOrder(editedOrder)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.minusIngredientQuantity(ingredientToAdd, editedOrder.getQuantity());

        try {
            model.setOrder(orderToEdit, editedOrder);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
        }

        return new CommandResult(
                String.format(MESSAGE_ADD_ORDER_INGREDIENT_SUCCESS, ingredientToAdd, editedOrder), UiTab.HOME);
    }

    private RecipeIngredientList makeEditedIngredientList(Ingredient ingredientToAdd, Order orderToEdit)
            throws CommandException {
        RecipeIngredientList originalRecipeIngredientList = orderToEdit.getRecipeIngredients();
        if (originalRecipeIngredientList.contains(ingredientToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER_INGREDIENT);
        }

        List<Ingredient> newIngredients = new ArrayList<>(originalRecipeIngredientList.getIngredients());
        newIngredients.add(ingredientToAdd);
        return new RecipeIngredientList(newIngredients);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddOrderIngredientCommand)) {
            return false;
        }

        // state check
        AddOrderIngredientCommand e = (AddOrderIngredientCommand) other;
        return index.equals(e.index)
                && ingredientDescriptor.equals(e.ingredientDescriptor);
    }
}
