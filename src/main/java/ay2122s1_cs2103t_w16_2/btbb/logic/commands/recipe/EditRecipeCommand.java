package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_RECIPE;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;
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
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Edits the details of an existing recipe in btbb.
 */
public class EditRecipeCommand extends Command {
    public static final String COMMAND_WORD = "edit-r";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the recipe identified "
            + "by the index number used in the displayed recipe bookmarks list.\n"
            + "\t   Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RECIPE_NAME + "RECIPE_NAME] "
            + "[" + PREFIX_RECIPE_PRICE + "RECIPE_PRICE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RECIPE_NAME + "Burger "
            + PREFIX_RECIPE_PRICE + "8 ";

    public static final String MESSAGE_EDIT_RECIPE_SUCCESS = "Edited Recipe: %1$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index index;
    private final RecipeDescriptor editRecipeDescriptor;

    /**
     * Constructs an EditRecipeCommand object.
     *
     * @param index The index of the recipe in the filtered recipe list.
     * @param editRecipeDescriptor Contains details to edit the recipe with.
     */
    public EditRecipeCommand(Index index, RecipeDescriptor editRecipeDescriptor) {
        requireAllNonNull(index, editRecipeDescriptor);

        this.index = index;
        this.editRecipeDescriptor = editRecipeDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + EditRecipeCommand.class.getSimpleName());

        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());
        Recipe editedRecipe = editRecipeDescriptor.toModelTypeFrom(recipeToEdit);

        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        try {
            model.setRecipe(recipeToEdit, editedRecipe);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        model.updateFilteredRecipeList(Model.PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(
                String.format(MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe), UiTab.HOME
        );
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecipeCommand)) {
            return false;
        }

        // state check
        EditRecipeCommand e = (EditRecipeCommand) other;
        return index.equals(e.index)
                && editRecipeDescriptor.equals(e.editRecipeDescriptor);
    }
}
