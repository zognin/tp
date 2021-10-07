package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

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
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the address book";

    private final IngredientDescriptor ingredientDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Ingredient}
     */
    public AddIngredientCommand(IngredientDescriptor ingredientDescriptor) {
        requireNonNull(ingredientDescriptor);
        this.ingredientDescriptor = ingredientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Ingredient ingredient = ingredientDescriptor.toModelType();

        if (model.hasIngredient(ingredient)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.addIngredient(ingredient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, ingredient));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddIngredientCommand // instanceof handles nulls
                && ingredientDescriptor.equals(((AddIngredientCommand) other).ingredientDescriptor));
    }
}
