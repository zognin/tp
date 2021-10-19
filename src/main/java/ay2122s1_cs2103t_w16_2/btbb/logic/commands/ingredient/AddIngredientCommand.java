package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds an ingredient to the address book.
 */
public class AddIngredientCommand extends Command {
    public static final String COMMAND_WORD = "add-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the address book. "
            + "Parameters: "
            + PREFIX_INGREDIENT_NAME + "NAME "
            + PREFIX_INGREDIENT_QUANTITY + "QUANTITY "
            + PREFIX_INGREDIENT_UNIT + "UNIT\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT_NAME + "Rice "
            + PREFIX_INGREDIENT_QUANTITY + "400 "
            + PREFIX_INGREDIENT_UNIT + "g";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in your inventory.";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final IngredientDescriptor ingredientDescriptor;

    /**
     * Creates an AddIngredientCommand to add the specified {@code Ingredient}.
     *
     * @param ingredientDescriptor given ingredient descriptor.
     */
    public AddIngredientCommand(IngredientDescriptor ingredientDescriptor) {
        requireNonNull(ingredientDescriptor);
        this.ingredientDescriptor = ingredientDescriptor;
    }

    /**
     * Executes this AddIngredientCommand and returns a CommandResult object.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + AddIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        Ingredient ingredient = ingredientDescriptor.toModelType();

        if (model.hasIngredient(ingredient)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.addIngredient(ingredient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredient), UiTab.INVENTORY_AND_STATISTICS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIngredientCommand // instanceof handles nulls
                && ingredientDescriptor.equals(((AddIngredientCommand) other).ingredientDescriptor));
    }
}
