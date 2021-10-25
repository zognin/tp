package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_RECIPES_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.RECIPE_NAME_LAKSA_PRATA_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.addPredicates;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeStringContainsKeywordsPredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_EGG_PRATA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.RECIPE_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) for {@code FindRecipeCommand}.
 */
public class FindRecipeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PredicateCollection<Recipe> predicateCollection = new PredicateCollection<>();
        addPredicates(predicateCollection, List.of(RECIPE_NAME_LAKSA_PRATA_PREDICATE));

        FindRecipeCommand findCommand = new FindRecipeCommand(predicateCollection);

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindRecipeCommand findCommandCopy = new FindRecipeCommand(predicateCollection);
        assertTrue(findCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(findCommand.equals(1));

        // null -> returns false
        assertFalse(findCommand.equals(null));
    }

    @Test
    public void execute_multipleKeywords_multipleRecipesFound() {
        String expectedMessage = String.format(MESSAGE_RECIPES_LISTED_OVERVIEW, 2);
        PredicateCollection<Recipe> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("laksa prata", Recipe::getName)
        );
        FindRecipeCommand command = new FindRecipeCommand(predicateCollection);
        expectedModel.updateFilteredRecipeList(predicateCollection);
        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
        assertEquals(Arrays.asList(RECIPE_EGG_PRATA, RECIPE_LAKSA), model.getFilteredRecipeList());
    }
}
