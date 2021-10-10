package ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INGREDIENT_NAME_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_FROM_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_QUANTITY_TO_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.QUANTITY_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.UNIT_DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_FROM;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_TO;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil.MESSAGE_INVALID_KEYWORD;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.INGREDIENT_QUANTITY_5_550_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.INGREDIENT_QUANTITY_FROM_5_TO_600_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollectionTest.INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.FindIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.PredicateCollection;
import org.junit.jupiter.api.Test;

public class FindIngredientCommandParserTest {
    private FindIngredientCommandParser parser = new FindIngredientCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindIngredientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noKeywords_throwsParseException() {
        // no keywords at all
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIngredientCommand.MESSAGE_USAGE));

        // prefixes with no keyword
        assertParseFailure(parser, " " + PREFIX_INGREDIENT_NAME + " " + PREFIX_INGREDIENT_QUANTITY
                + " " + PREFIX_INGREDIENT_UNIT, MESSAGE_INVALID_KEYWORD);
    }

    @Test
    public void parse_validArgs_returnsFindIngredientCommand() {
        PredicateCollection<Ingredient> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_QUANTITY_5_550_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_QUANTITY_FROM_5_TO_600_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE);
        FindIngredientCommand expectedFindIngredientCommand = new FindIngredientCommand(predicateCollection);

        assertParseSuccess(parser, " "
                + PREFIX_INGREDIENT_NAME + "Avocado Butter "
                + PREFIX_INGREDIENT_QUANTITY + "5 550 "
                + PREFIX_INGREDIENT_QUANTITY_FROM + "5 " + PREFIX_INGREDIENT_QUANTITY_TO + "600 "
                + PREFIX_INGREDIENT_UNIT + "whole grams"
                , expectedFindIngredientCommand);
    }

    @Test
    public void parse_invalidArgs_failure() {
        // invalid quantity
        assertParseFailure(parser, INVALID_QUANTITY_DESC, Quantity.MESSAGE_INTERNAL_CONSTRAINTS);
        assertParseFailure(parser, INVALID_QUANTITY_FROM_DESC, Quantity.MESSAGE_INTERNAL_CONSTRAINTS);
        assertParseFailure(parser, INVALID_QUANTITY_TO_DESC, Quantity.MESSAGE_INTERNAL_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_QUANTITY_DESC + PREFIX_INGREDIENT_UNIT,
                Quantity.MESSAGE_INTERNAL_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + INGREDIENT_NAME_DESC_BEEF + QUANTITY_DESC_BEEF + UNIT_DESC_BEEF,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindIngredientCommand.MESSAGE_USAGE));
    }
}
