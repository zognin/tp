package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.FindIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;

/**
 * Parses input arguments and creates a new FindIngredientCommand object
 */
public class FindIngredientCommandParser implements Parser<FindIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindIngredientCommand
     * and returns a FindIngredientCommand object for execution.
     *
     * @param args Arguments to parse.
     * @return {@code FindIngredientCommand}.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindIngredientCommand parse(String args) throws ParseException {
        requireAllNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT);

        PredicateCollection<Ingredient> predicateCollection = new PredicateCollection<>();
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_INGREDIENT_NAME, argMultimap, Ingredient::getName
        );
        predicateCollection.addQuantityEqualsKeywordsPredicate(
                PREFIX_INGREDIENT_QUANTITY, argMultimap, Ingredient::getQuantity
        );
        predicateCollection.addStringContainsKeywordsPredicate(
                PREFIX_INGREDIENT_UNIT, argMultimap, Ingredient::getUnit
        );

        if (predicateCollection.hasNoPredicates() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIngredientCommand.MESSAGE_USAGE));
        }

        return new FindIngredientCommand(predicateCollection);
    }
}
