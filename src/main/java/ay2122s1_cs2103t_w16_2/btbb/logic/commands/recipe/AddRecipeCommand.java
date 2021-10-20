package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds a recipe to btbb.
 */
public class AddRecipeCommand extends Command {
    public static final String COMMAND_WORD = "add-r";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to your recipe bookmarks.\n"
            + "Parameters: "
            + PREFIX_RECIPE_NAME + "RECIPE_NAME "
            + "[" + PREFIX_RECIPE_INGREDIENT + "INGREDIENT_NAME-QUANTITY-UNIT, ...] "
            + PREFIX_RECIPE_PRICE + "RECIPE_PRICE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_RECIPE_NAME + "Soup "
            + PREFIX_RECIPE_INGREDIENT + "Carrot-2-Stick, Egg-1-Whole "
            + PREFIX_RECIPE_PRICE + "2.00";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in your recipe bookmarks";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final RecipeDescriptor recipeDescriptor;

    /**
     * Creates an AddRecipeCommand to add the specified {@code Recipe}
     */
    public AddRecipeCommand(RecipeDescriptor recipeDescriptor) {
        requireNonNull(recipeDescriptor);
        this.recipeDescriptor = recipeDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + AddRecipeCommand.class.getSimpleName());

        requireNonNull(model);
        Recipe recipe = recipeDescriptor.toModelType();

        if (model.hasRecipe(recipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(recipe);
        return new CommandResult(String.format(MESSAGE_SUCCESS, recipe), UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecipeCommand // instanceof handles nulls
                && recipeDescriptor.equals(((AddRecipeCommand) other).recipeDescriptor));
    }
}
