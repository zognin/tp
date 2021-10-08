package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new AddIngredientCommand object.
 */
public class AddIngredientCommandParser implements Parser<AddIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddIngredientCommand
     * and returns an AddIngredientCommand object for execution.
     *
     * @param args String input.
     * @return AddIngredientCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddIngredientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap,
                PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
        }

        IngredientDescriptor ingredientDescriptor = new IngredientDescriptor();
        ingredientDescriptor.setName(ParserUtil.parseGenericString(
                argMultimap.getValue(PREFIX_INGREDIENT_NAME).get()));
        ingredientDescriptor.setQuantity(ParserUtil.parseQuantity(
                argMultimap.getValue(PREFIX_INGREDIENT_QUANTITY).get()));
        ingredientDescriptor.setUnit(ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_INGREDIENT_UNIT).get()));

        return new AddIngredientCommand(ingredientDescriptor);
    }
}
