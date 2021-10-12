package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_INDEX;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

public class DeleteOrderIngredientCommandParser implements Parser<DeleteOrderIngredientCommand> {
    @Override
    public DeleteOrderIngredientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT_INDEX);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_INGREDIENT_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteOrderIngredientCommand.MESSAGE_USAGE));
        }

        Index ingredientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INGREDIENT_INDEX).get());
        Index orderIndex;

        try {
            orderIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderIngredientCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteOrderIngredientCommand(ingredientIndex, orderIndex);
    }
}
