package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalRecipes.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListRecipeCommand.
 */
public class ListRecipeCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccessWithTabChange(new ListRecipeCommand(), model, ListRecipeCommand.MESSAGE_SUCCESS,
                expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredRecipeList(unused -> false);
        assertCommandSuccessWithTabChange(new ListRecipeCommand(), model, ListRecipeCommand.MESSAGE_SUCCESS,
                expectedModel, UiTab.HOME);
    }
}
