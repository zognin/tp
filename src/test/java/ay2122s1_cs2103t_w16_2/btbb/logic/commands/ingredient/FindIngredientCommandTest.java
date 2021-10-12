package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INGREDIENTS_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_QUANTITY_5_550_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.addPredicates;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeQuantityInListPredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeQuantityWithinRangePredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeSpaceSeparatedStringKeywords;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeStringContainsKeywordsPredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BUTTER;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.CHICKEN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) for {@code FindIngredientCommand}.
 */
public class FindIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PredicateCollection<Ingredient> predicateCollection = new PredicateCollection<>();
        addPredicates(predicateCollection, List.of(
                INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE,
                INGREDIENT_QUANTITY_5_550_PREDICATE,
                INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE));

        PredicateCollection<Ingredient> diffOrderPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderPredicateCollection, List.of(
                INGREDIENT_QUANTITY_5_550_PREDICATE,
                INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE,
                INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE));

        PredicateCollection<Ingredient> diffAmountPredicateCollection = new PredicateCollection<>();
        addPredicates(diffAmountPredicateCollection, List.of(INGREDIENT_QUANTITY_5_550_PREDICATE));

        FindIngredientCommand findCommand = new FindIngredientCommand(predicateCollection);
        FindIngredientCommand diffOrderFindCommand = new FindIngredientCommand(diffOrderPredicateCollection);
        FindIngredientCommand diffAmountFindCommand = new FindIngredientCommand(diffAmountPredicateCollection);

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindIngredientCommand findCommandCopy = new FindIngredientCommand(predicateCollection);
        assertTrue(findCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(findCommand.equals(1));

        // null -> returns false
        assertFalse(findCommand.equals(null));

        // different order of ingredient predicates -> returns true
        assertTrue(findCommand.equals(diffOrderFindCommand));

        // different amount of ingredient predicates -> returns false
        assertFalse(findCommand.equals(diffAmountFindCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleIngredientsFound() {
        String expectedMessage = String.format(MESSAGE_INGREDIENTS_LISTED_OVERVIEW, 3);
        PredicateCollection<Ingredient> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate(
                        makeSpaceSeparatedStringKeywords(AVOCADO.getName(), BUTTER.getName(), CHICKEN.getName()),
                        Ingredient::getName)
        );
        predicateCollection.addPredicate(
                makeQuantityInListPredicate(makeSpaceSeparatedStringKeywords(AVOCADO.getQuantity(),
                        BUTTER.getQuantity(), CHICKEN.getQuantity()),
                        Ingredient::getQuantity)
        );
        predicateCollection.addPredicate(
                makeQuantityWithinRangePredicate(
                        AVOCADO.getQuantity().toString(), BUTTER.getQuantity().toString(),
                        Ingredient::getQuantity)
        );
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate(
                        makeSpaceSeparatedStringKeywords(AVOCADO.getUnit(), BUTTER.getUnit(), CHICKEN.getUnit()),
                        Ingredient::getUnit)
        );
        FindIngredientCommand command = new FindIngredientCommand(predicateCollection);
        expectedModel.updateFilteredIngredientList(predicateCollection);
        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.INVENTORY);
        assertEquals(Arrays.asList(AVOCADO, BUTTER, CHICKEN), model.getFilteredIngredientList());
    }
}
