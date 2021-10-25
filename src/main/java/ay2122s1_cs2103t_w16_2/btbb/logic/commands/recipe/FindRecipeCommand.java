package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Finds and lists all recipes in btbb whose recipe name matches the provided parameters.
 */
public class FindRecipeCommand extends Command {
    public static final String COMMAND_WORD = "find-r";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds recipes by "
            + "recipe name field. \n"
            + "Parameters: "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RECIPE_NAME + "chocolate";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private PredicateCollection<Recipe> predicateCollection;

    public FindRecipeCommand(PredicateCollection<Recipe> predicateCollection) {
        this.predicateCollection = predicateCollection;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + FindRecipeCommand.class.getSimpleName());

        requireNonNull(model);
        model.updateFilteredRecipeList(predicateCollection);
        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()),
                UiTab.HOME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindRecipeCommand)
                && predicateCollection.equals(((FindRecipeCommand) other).predicateCollection);
    }
}
