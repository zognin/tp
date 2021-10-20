package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;

/**
 * Parses input arguments and creates a new AddRecipeCommand object.
 */
public class AddRecipeCommandParser implements Parser<AddRecipeCommand> {
    @Override
    public AddRecipeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_RECIPE_NAME, PREFIX_RECIPE_INGREDIENT, PREFIX_RECIPE_PRICE);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_RECIPE_NAME, PREFIX_RECIPE_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }

        RecipeDescriptor recipeDescriptor = new RecipeDescriptor();
        recipeDescriptor.setName(ParserUtil.parseGenericString(
                argMultimap.getValue(PREFIX_RECIPE_NAME).get(), "Recipe Name"));
        recipeDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_RECIPE_PRICE).get()));
        if (argMultimap.getValue(PREFIX_RECIPE_INGREDIENT).isEmpty()) {
            // If the user does not provide recipe ingredients, it will be set to an empty list
            recipeDescriptor.setRecipeIngredients(new RecipeIngredientList(new ArrayList<>()));
        } else {
            recipeDescriptor.setRecipeIngredients(ParserUtil
                    .parseRecipeIngredients(argMultimap.getValue(PREFIX_RECIPE_INGREDIENT).get()));
        }

        return new AddRecipeCommand(recipeDescriptor);
    }
}
