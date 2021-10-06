package ay2122s1_cs2103t_w16_2.btbb.logic.commands.general;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class TabCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        UiTab tabToChangeTo = UiTab.values()[INDEX_FIRST.getZeroBased()];
        TabCommand tabCommand = new TabCommand(INDEX_FIRST);

        String expectedMessage = String.format(TabCommand.MESSAGE_CHANGE_TAB_SUCCESS, tabToChangeTo);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccessWithTabChange(tabCommand, model, expectedMessage, expectedModel, tabToChangeTo);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(UiTab.values().length + 1);
        TabCommand tabCommand = new TabCommand(outOfBoundIndex);

        assertCommandFailure(tabCommand, model, TabCommand.MESSAGE_INVALID_TAB_INDEX);
    }

    @Test
    public void equals() {
        TabCommand tabFirstCommand = new TabCommand(INDEX_FIRST);
        TabCommand tabSecondCommand = new TabCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(tabFirstCommand.equals(tabFirstCommand));

        // same values -> returns true
        TabCommand tabFirstCommandCopy = new TabCommand(INDEX_FIRST);
        assertTrue(tabFirstCommand.equals(tabFirstCommandCopy));

        // different types -> returns false
        assertFalse(tabFirstCommand.equals(1));

        // null -> returns false
        assertFalse(tabFirstCommand.equals(null));

        // different tab -> returns false
        assertFalse(tabFirstCommand.equals(tabSecondCommand));
    }
}
