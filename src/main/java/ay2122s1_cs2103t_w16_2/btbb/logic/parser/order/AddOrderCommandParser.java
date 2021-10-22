package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand.MESSAGE_USAGE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_DEADLINE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_PRICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ORDER_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_INGREDIENT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_RECIPE_NAME;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Parses input arguments and creates a new AddOrderCommand object.
 */
public class AddOrderCommandParser implements Parser<AddOrderCommand> {
    @Override
    public AddOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_INDEX, PREFIX_CLIENT_NAME,
                PREFIX_CLIENT_PHONE, PREFIX_CLIENT_ADDRESS, PREFIX_RECIPE_INDEX, PREFIX_RECIPE_NAME,
                PREFIX_RECIPE_INGREDIENT, PREFIX_ORDER_PRICE, PREFIX_ORDER_DEADLINE, PREFIX_ORDER_QUANTITY);

        if (!areArgumentsValid(argMultimap)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        OrderDescriptor orderDescriptor = new OrderDescriptor();
        fillOrderDescriptorClientFields(orderDescriptor, argMultimap);
        fillOrderDescriptorOrderDetailFields(orderDescriptor, argMultimap);
        fillOrderDescriptorRecipeFields(orderDescriptor, argMultimap);
        return new AddOrderCommand(orderDescriptor);
    }

    private boolean areArgumentsValid(ArgumentMultimap argMultimap) {
        boolean isClientIndexPresent = ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_CLIENT_INDEX);
        boolean areClientFieldsPresent = ParserUtil.areAllPrefixesPresent(argMultimap,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_ADDRESS);
        boolean isRecipeIndexPresent = ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_RECIPE_INDEX);
        boolean areRecipeFieldsPresent = ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_RECIPE_NAME);
        boolean isOrderPricePresent = ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_ORDER_PRICE);
        boolean areCompulsoryOrderDetailsPresent = ParserUtil.areAllPrefixesPresent(argMultimap,
                PREFIX_ORDER_DEADLINE);
        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();

        return (isClientIndexPresent || areClientFieldsPresent)
                && (isRecipeIndexPresent || areRecipeFieldsPresent)
                && (isRecipeIndexPresent || isOrderPricePresent)
                && areCompulsoryOrderDetailsPresent
                && isPreambleEmpty;
    }

    private void fillOrderDescriptorClientFields(OrderDescriptor orderDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_CLIENT_INDEX).isPresent()) {
            orderDescriptor.setClientIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLIENT_INDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            orderDescriptor.setClientName(ParserUtil
                    .parseGenericString(argMultimap.getValue(PREFIX_CLIENT_NAME).get(), "Name"));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_PHONE).isPresent()) {
            orderDescriptor.setClientPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ADDRESS).isPresent()) {
            orderDescriptor
                    .setClientAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_CLIENT_ADDRESS).get()));
        }
    }

    private void fillOrderDescriptorOrderDetailFields(OrderDescriptor orderDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        orderDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_ORDER_DEADLINE).get()));

        if (argMultimap.getValue(PREFIX_ORDER_PRICE).isPresent()) {
            orderDescriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_ORDER_PRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_ORDER_QUANTITY).isEmpty()) {
            // If the user does not provide the order quantity, it will be set to a default value of 1
            orderDescriptor.setQuantity(new Quantity("1"));
        } else {
            orderDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ORDER_QUANTITY).get()));
        }
        // When a new Order is added, completionStatus is set to false by default
        orderDescriptor.setCompletionStatus(new CompletionStatus(false));
    }

    private void fillOrderDescriptorRecipeFields(OrderDescriptor orderDescriptor, ArgumentMultimap argMultimap)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_RECIPE_INDEX).isPresent()) {
            orderDescriptor.setRecipeIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RECIPE_INDEX).get()));
        }
        if (argMultimap.getValue(PREFIX_RECIPE_NAME).isPresent()) {
            orderDescriptor.setRecipeName(ParserUtil
                    .parseGenericString(argMultimap.getValue(PREFIX_RECIPE_NAME).get(), "Recipe Name"));
        }
        if (argMultimap.getValue(PREFIX_RECIPE_INGREDIENT).isEmpty()) {
            // If the user does not provide recipe ingredients, it will be set to an empty list
            orderDescriptor.setRecipeIngredients(new RecipeIngredientList(new ArrayList<>()));
        } else {
            orderDescriptor.setRecipeIngredients(ParserUtil
                    .parseRecipeIngredients(argMultimap.getValue(PREFIX_RECIPE_INGREDIENT).get()));
        }
    }
}
