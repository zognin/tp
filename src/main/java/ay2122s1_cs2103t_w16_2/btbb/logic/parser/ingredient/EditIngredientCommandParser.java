package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_NOT_EDITED;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.EditIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new EditIngredientCommand object
 */
public class EditIngredientCommandParser implements Parser<EditIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditIngredientCommand
     * and returns an EditIngredientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_NAME,
                PREFIX_INGREDIENT_QUANTITY, PREFIX_INGREDIENT_UNIT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIngredientCommand.MESSAGE_USAGE),
                    pe);
        }

        IngredientDescriptor editIngredientDescriptor = new IngredientDescriptor();
        fillIngredientDescriptor(argMultimap, editIngredientDescriptor);

        if (!editIngredientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditIngredientCommand(index, editIngredientDescriptor);
    }

    private void fillIngredientDescriptor(ArgumentMultimap argMultimap,
                                          IngredientDescriptor ingredientDescriptor) throws ParseException {
        if (argMultimap.getValue(PREFIX_INGREDIENT_NAME).isPresent()) {
            ingredientDescriptor.setName(
                    ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_INGREDIENT_NAME).get(), "Name"));
        }
        if (argMultimap.getValue(PREFIX_INGREDIENT_QUANTITY).isPresent()) {
            ingredientDescriptor.setQuantity(
                    ParserUtil.parseInternalQuantity(argMultimap.getValue(PREFIX_INGREDIENT_QUANTITY).get()));
        }
        if (argMultimap.getValue(PREFIX_INGREDIENT_UNIT).isPresent()) {
            ingredientDescriptor.setUnit(
                    ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_INGREDIENT_UNIT).get(), "Unit"));
        }
    }
}
