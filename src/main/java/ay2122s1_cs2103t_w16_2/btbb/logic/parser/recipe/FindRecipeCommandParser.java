package ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.FindRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;

/**
 * Parses input arguments and creates a new FindRecipeCommand object.
 */
public class FindRecipeCommandParser implements Parser<FindRecipeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindRecipeCommand
     * and returns a FindRecipeCommand object for execution.
     *
     * @param args Arguments to parse.
     * @return {@code FindRecipeCommand}.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public FindRecipeCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_NAME);

        PredicateCollection<Recipe> predicateCollection = new PredicateCollection<>();
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_RECIPE_NAME, argMultimap, Recipe::getName
        );

        if (predicateCollection.hasNoPredicates() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRecipeCommand.MESSAGE_USAGE));
        }
        return new FindRecipeCommand(predicateCollection);
    }
}
