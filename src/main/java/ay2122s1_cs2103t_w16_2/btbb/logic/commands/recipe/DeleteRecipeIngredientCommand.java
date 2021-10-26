package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_RECIPE;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.util.CommandUtil.makeRecipeWithEditedIngredients;
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
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Deletes an ingredient from an existing recipe in btbb.
 */
public class DeleteRecipeIngredientCommand extends Command {
    public static final String COMMAND_WORD = "delete-ri";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ingredient identified by the index number in a recipe identified by an index number.\n"
            + "Parameters: RECIPE_INDEX (must be a positive integer) "
            + PREFIX_INGREDIENT_INDEX + "INGREDIENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INGREDIENT_INDEX + "2";

    public static final String MESSAGE_DELETE_RECIPE_INGREDIENT_SUCCESS = "Deleted Recipe Ingredient: %1$s\n"
            + "Edited Recipe: %2$s";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index targetIngredientIndex;
    private final Index targetRecipeIndex;

    /**
     * Creates an DeleteRecipeIngredientCommand to delete the specified ingredient,
     * identified by its index in the recipe ingredient list.
     *
     * @param targetIngredientIndex Index of ingredient in the recipe ingredient list.
     * @param targetRecipeIndex Index of the recipe in the displayed list.
     */
    public DeleteRecipeIngredientCommand(Index targetIngredientIndex, Index targetRecipeIndex) {
        requireAllNonNull(targetIngredientIndex, targetRecipeIndex);

        this.targetIngredientIndex = targetIngredientIndex;
        this.targetRecipeIndex = targetRecipeIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + DeleteRecipeIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        List<Recipe> lastShownRecipeList = model.getFilteredRecipeList();

        if (targetRecipeIndex.getZeroBased() >= lastShownRecipeList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownRecipeList.get(targetRecipeIndex.getZeroBased());
        Ingredient ingredientToDelete = getIngredientToDelete(recipeToEdit);

        RecipeIngredientList editedIngredientList = makeEditedIngredientList(recipeToEdit);
        Recipe editedRecipe = makeRecipeWithEditedIngredients(editedIngredientList, recipeToEdit);
        if (!recipeToEdit.isSameRecipe(editedRecipe) && model.hasRecipe(editedRecipe)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        try {
            model.setRecipe(recipeToEdit, editedRecipe);
        } catch (NotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        return new CommandResult(
                String.format(MESSAGE_DELETE_RECIPE_INGREDIENT_SUCCESS, ingredientToDelete, editedRecipe), UiTab.HOME);
    }

    private Ingredient getIngredientToDelete(Recipe recipeToEdit) throws CommandException {
        RecipeIngredientList originalRecipeIngredientList = recipeToEdit.getRecipeIngredients();

        if (targetIngredientIndex.getZeroBased() >= originalRecipeIngredientList.getIngredients().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        return originalRecipeIngredientList.getIngredients().get(targetIngredientIndex.getZeroBased());
    }

    private RecipeIngredientList makeEditedIngredientList(Recipe recipeToEdit) {
        RecipeIngredientList originalRecipeIngredientList = recipeToEdit.getRecipeIngredients();

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
        if (!(other instanceof DeleteRecipeIngredientCommand)) {
            return false;
        }

        // state check
        DeleteRecipeIngredientCommand e = (DeleteRecipeIngredientCommand) other;
        return targetIngredientIndex.equals(e.targetIngredientIndex)
                && targetRecipeIndex.equals(e.targetRecipeIndex);
    }
}
