package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class DeleteIngredientCommand extends Command {
    public static final String COMMAND_WORD = "delete-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ingredient identified by the index number used in the displayed ingredient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Deleted Ingredient: %1$s";

    private final Index targetIndex;

    public DeleteIngredientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToDelete = lastShownList.get(targetIndex.getZeroBased());

        try {
            model.deleteIngredient(ingredientToDelete);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete), UiTab.INVENTORY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIngredientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteIngredientCommand) other).targetIndex)); // state check
    }
}
