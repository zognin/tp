package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_ORDER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds an order to btbb.
 */
public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add-o";

    // Provide both client and recipe details
    public static final String NO_INDEX = PREFIX_CLIENT_NAME + "CLIENT_NAME "
            + PREFIX_CLIENT_PHONE + "CLIENT_PHONE "
            + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS "
            + PREFIX_RECIPE_NAME + "RECIPE_NAME "
            + "[" + PREFIX_RECIPE_INGREDIENT + "INGREDIENT_NAME-QUANTITY-UNIT, ...] "
            + PREFIX_ORDER_PRICE + "ORDER_PRICE "
            + PREFIX_ORDER_DEADLINE + "ORDER_DEADLINE "
            + "[" + PREFIX_ORDER_QUANTITY + "ORDER_QUANTITY]\n";

    // Only client index provided
    public static final String CLIENT_INDEX = PREFIX_CLIENT_INDEX + "CLIENT_INDEX "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "CLIENT_PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS] "
            + PREFIX_RECIPE_NAME + "RECIPE_NAME "
            + "[" + PREFIX_RECIPE_INGREDIENT + "INGREDIENT_NAME-QUANTITY-UNIT, ...] "
            + PREFIX_ORDER_PRICE + "ORDER_PRICE "
            + PREFIX_ORDER_DEADLINE + "ORDER_DEADLINE "
            + "[" + PREFIX_ORDER_QUANTITY + "ORDER_QUANTITY]\n";

    // Only recipe index provided
    public static final String RECIPE_INDEX = PREFIX_CLIENT_NAME + "CLIENT_NAME "
            + PREFIX_CLIENT_PHONE + "CLIENT_PHONE "
            + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS "
            + PREFIX_RECIPE_INDEX + "RECIPE_INDEX "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME] "
            + "[" + PREFIX_RECIPE_INGREDIENT + "INGREDIENT_NAME-QUANTITY-UNIT, ...] "
            + "[" + PREFIX_ORDER_PRICE + "ORDER_PRICE] "
            + PREFIX_ORDER_DEADLINE + "ORDER_DEADLINE "
            + "[" + PREFIX_ORDER_QUANTITY + "ORDER_QUANTITY]\n";

    // Both client and recipe indexes provided
    public static final String CLIENT_AND_RECIPE_INDEX = PREFIX_CLIENT_INDEX + "CLIENT_INDEX "
            + "[" + PREFIX_CLIENT_NAME + "CLIENT_NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "CLIENT_PHONE] "
            + "[" + PREFIX_CLIENT_ADDRESS + "CLIENT_ADDRESS] "
            + PREFIX_RECIPE_INDEX + "RECIPE_INDEX "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME] "
            + "[" + PREFIX_RECIPE_INGREDIENT + "INGREDIENT_NAME-QUANTITY-UNIT, ...] "
            + "[" + PREFIX_ORDER_PRICE + "ORDER_PRICE] "
            + PREFIX_ORDER_DEADLINE + "ORDER_DEADLINE "
            + "[" + PREFIX_ORDER_QUANTITY + "ORDER_QUANTITY]\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to your order list.\n"
            + "Possible combinations of Parameters: \n"
            + "1. " + NO_INDEX
            + "2. " + CLIENT_INDEX
            + "3. " + RECIPE_INDEX
            + "4. " + CLIENT_AND_RECIPE_INDEX
            + "Additional info: If ORDER_QUANTITY is not present, it will be set to 1 by default.";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

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
        logger.info("Executing " + AddOrderCommand.class.getSimpleName());

        requireNonNull(model);
        Order order = orderDescriptor.toModelType(model);

        if (model.hasOrder(order)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        order.getRecipeIngredients().getIngredients().forEach(ingredient -> {
            model.minusIngredientQuantity(ingredient, order.getQuantity());
        });

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
