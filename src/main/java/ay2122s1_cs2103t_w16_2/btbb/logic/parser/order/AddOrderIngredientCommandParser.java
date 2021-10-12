package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new AddOrderIngredientCommand object.
 */
public class AddOrderIngredientCommandParser implements Parser<AddOrderIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddOrderIngredientCommand
     * and returns an AddOrderIngredientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddOrderIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap,
                PREFIX_INGREDIENT_NAME, PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddOrderIngredientCommand.MESSAGE_USAGE));
        }

        IngredientDescriptor ingredientDescriptor = new IngredientDescriptor();
        ingredientDescriptor.setName(ParserUtil.parseGenericString(
                argMultimap.getValue(PREFIX_INGREDIENT_NAME).get(), "Name"));
        ingredientDescriptor.setQuantity(ParserUtil.parseQuantity(
                argMultimap.getValue(PREFIX_INGREDIENT_QUANTITY).get()));
        ingredientDescriptor.setUnit(ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_INGREDIENT_UNIT).get(),
                "Unit"));

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderIngredientCommand.MESSAGE_USAGE), pe);
        }

        return new AddOrderIngredientCommand(index, ingredientDescriptor);
    }
}
