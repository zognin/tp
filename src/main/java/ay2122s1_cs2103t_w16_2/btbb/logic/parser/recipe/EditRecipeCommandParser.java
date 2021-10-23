package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_PRICE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.EditRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new EditRecipeCommand object
 */
public class EditRecipeCommandParser implements Parser<EditRecipeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditRecipeCommand
     * and returns an EditRecipeCommand object for execution.
     *
     * @param args String input.
     * @return EditRecipeCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public EditRecipeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_NAME, PREFIX_RECIPE_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRecipeCommand.MESSAGE_USAGE),
                    pe);
        }

        RecipeDescriptor editRecipeDescriptor = new RecipeDescriptor();
        fillRecipeDescriptor(argMultimap, editRecipeDescriptor);

        if (!editRecipeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRecipeCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecipeCommand(index, editRecipeDescriptor);
    }

    private void fillRecipeDescriptor(ArgumentMultimap argMultimap, RecipeDescriptor recipeDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_RECIPE_NAME).isPresent()) {
            recipeDescriptor.setName(ParserUtil.parseGenericString(
                    argMultimap.getValue(PREFIX_RECIPE_NAME).get(), "Recipe Name"));
        }

        if (argMultimap.getValue(PREFIX_RECIPE_PRICE).isPresent()) {
            recipeDescriptor.setRecipePrice(
                    ParserUtil.parseRecipePrice(argMultimap.getValue(PREFIX_RECIPE_PRICE).get()));
        }
    }
}
