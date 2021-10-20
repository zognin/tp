package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_FROM;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_TO;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Finds and lists all ingredients in btbb whose name, quantity or unit
 * matches the provided parameters.
 */
public class FindIngredientCommand extends Command {
    public static final String COMMAND_WORD = "find-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds ingredients by "
            + "name, quantity or unit fields. \n"
            + "Parameters (at least one must be provided): "
            + "[" + PREFIX_INGREDIENT_NAME + "NAME] "
            + "[" + PREFIX_INGREDIENT_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_INGREDIENT_QUANTITY_FROM + "QUANTITY_FROM] "
            + "[" + PREFIX_INGREDIENT_QUANTITY_TO + "QUANTITY_TO] "
            + "[" + PREFIX_INGREDIENT_UNIT + "UNIT]\n"
            + "Additional Info: For " + PREFIX_INGREDIENT_QUANTITY
            + ", multiple space separated quantities can be provided."
            + " If both " + PREFIX_INGREDIENT_QUANTITY
            + " and (" + PREFIX_INGREDIENT_QUANTITY_FROM
            + " or " + PREFIX_INGREDIENT_QUANTITY_TO + ") are provided,"
            + " the found ingredients must fulfill all 3 conditions.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_INGREDIENT_NAME + "corn"
            + " " + PREFIX_INGREDIENT_QUANTITY + "30 20 15";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    private final PredicateCollection<Ingredient> predicateCollection;

    public FindIngredientCommand(PredicateCollection<Ingredient> predicateCollection) {
        this.predicateCollection = predicateCollection;
    }

    @Override
    public CommandResult execute(Model model) {
        logger.info("Executing " + FindIngredientCommand.class.getSimpleName());

        requireNonNull(model);
        model.updateFilteredIngredientList(predicateCollection);
        return new CommandResult(
                String.format(Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW, model.getFilteredIngredientList().size()),
                UiTab.INVENTORY_AND_STATISTICS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindIngredientCommand // instanceof handles nulls
                && predicateCollection.equals(((FindIngredientCommand) other).predicateCollection)); // state check
    }
}
