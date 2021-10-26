package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_RECIPE;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.util.CommandUtil.makeRecipeWithEditedIngredients;
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
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Adds an ingredient to an existing recipe in btbb.
 */
public class AddRecipeIngredientCommand extends Command {
    public static final String COMMAND_WORD = "add-ri";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an ingredient to the recipe identified "
            + "by the index number used in the displayed recipe bookmarks list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INGREDIENT_NAME + "INGREDIENT_NAME "
            + PREFIX_INGREDIENT_QUANTITY + "INGREDIENT_QUANTITY "
            + PREFIX_INGREDIENT_UNIT + "INGREDIENT_UNIT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INGREDIENT_NAME + "Rice "
            + PREFIX_INGREDIENT_QUANTITY + "400 "
            + PREFIX_INGREDIENT_UNIT + "g";

    public static final String MESSAGE_ADD_RECIPE_INGREDIENT_SUCCESS = "Added Recipe Ingredient: %1$s\n"
            + "Edited Recipe: %2$s";
    public static final String MESSAGE_DUPLICATE_RECIPE_INGREDIENT = "This ingredient already exists in the recipe";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final Index index;
    private final IngredientDescriptor ingredientDescriptor;

    /**
     * Creates an AddRecipeIngredientCommand to add the specified {@code Ingredient}.
     *
     * @param index of the recipe in the filtered recipe list.
     * @param ingredientDescriptor of ingredient to add to the recipe.
     */
    public AddRecipeIngredientCommand(Index index, IngredientDescriptor ingredientDescriptor) {
        requireAllNonNull(index, ingredientDescriptor);

        this.index = index;
        this.ingredientDescriptor = ingredientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.info("Executing " + AddRecipeIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Ingredient ingredientToAdd = ingredientDescriptor.toModelType();
        Recipe recipeToEdit = lastShownList.get(index.getZeroBased());

        RecipeIngredientList editedIngredientList = makeEditedIngredientList(ingredientToAdd, recipeToEdit);
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
                String.format(MESSAGE_ADD_RECIPE_INGREDIENT_SUCCESS, ingredientToAdd, editedRecipe), UiTab.HOME);
    }

    private RecipeIngredientList makeEditedIngredientList(Ingredient ingredientToAdd, Recipe recipeToEdit)
            throws CommandException {
        RecipeIngredientList originalRecipeIngredientList = recipeToEdit.getRecipeIngredients();
        if (originalRecipeIngredientList.contains(ingredientToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE_INGREDIENT);
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
        if (!(other instanceof AddRecipeIngredientCommand)) {
            return false;
        }

        // state check
        AddRecipeIngredientCommand e = (AddRecipeIngredientCommand) other;
        return index.equals(e.index)
                && ingredientDescriptor.equals(e.ingredientDescriptor);
    }
}
