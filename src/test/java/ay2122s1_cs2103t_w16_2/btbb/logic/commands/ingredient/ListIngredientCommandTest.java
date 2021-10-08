package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListIngredientCommand.
 */
public class ListIngredientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListIngredientCommand(), model, ListIngredientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        model.updateFilteredIngredientList(unused -> false);
        assertCommandSuccess(new ListIngredientCommand(), model, ListIngredientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
