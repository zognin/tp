package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.EditOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new EditOrderCommand object
 */
public class EditOrderCommandParser implements Parser<EditOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditOrderCommand
     * and returns an EditOrderCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    @Override
    public EditOrderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_ADDRESS, PREFIX_RECIPE_INDEX, PREFIX_RECIPE_NAME,
                PREFIX_ORDER_PRICE, PREFIX_ORDER_DEADLINE, PREFIX_ORDER_QUANTITY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOrderCommand.MESSAGE_USAGE), pe);
        }

        OrderDescriptor editOrderDescriptor = new OrderDescriptor();
        fillOrderDescriptor(argMultimap, editOrderDescriptor);

        if (!editOrderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditOrderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditOrderCommand(index, editOrderDescriptor);
    }

    private void fillOrderDescriptor(ArgumentMultimap argMultimap, OrderDescriptor orderDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_CLIENT_INDEX).isPresent()) {
            orderDescriptor.setClientIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            orderDescriptor.setClientName(
                    ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_CLIENT_NAME).get(), "Name"));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_PHONE).isPresent()) {
            orderDescriptor.setClientPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ADDRESS).isPresent()) {
            orderDescriptor.setClientAddress(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_CLIENT_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_RECIPE_INDEX).isPresent()) {
            orderDescriptor.setRecipeIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RECIPE_INDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_RECIPE_NAME).isPresent()) {
            orderDescriptor.setRecipeName(
                    ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_RECIPE_NAME).get(),
                            "Recipe Name"));
        }
        if (argMultimap.getValue(PREFIX_ORDER_PRICE).isPresent()) {
            orderDescriptor.setOrderPrice(
                    ParserUtil.parseOrderPrice(argMultimap.getValue(PREFIX_ORDER_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_ORDER_DEADLINE).isPresent()) {
            orderDescriptor.setDeadline(
                    ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_ORDER_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_ORDER_QUANTITY).isPresent()) {
            orderDescriptor.setQuantity(
                    ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ORDER_QUANTITY).get()));
        }
    }
}
