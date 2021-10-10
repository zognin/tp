package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class CommandResultTest {
    @Test
    public void isSwitchTab_hasTabToSwitchTo_true() {
        CommandResult commandResult = new CommandResult("feedback", UiTab.HOME);
        assertTrue(commandResult.isSwitchTab());
    }

    @Test
    public void isSwitchTab_noTabToSwitchTo_false() {
        CommandResult commandResult = new CommandResult("feedback");
        assertFalse(commandResult.isSwitchTab());
    }

    @Test
    public void getTabToSwitchToTest() {
        CommandResult commandResultWithTabToSwitchTo = new CommandResult("feedback", UiTab.HOME);
        assertEquals(commandResultWithTabToSwitchTo.getTabToSwitchTo(), UiTab.HOME);

        CommandResult commandResultWithoutTabToSwitchTo = new CommandResult("feedback");
        assertEquals(commandResultWithoutTabToSwitchTo.getTabToSwitchTo(), null);
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");
        CommandResult commandResultWithTabToSwitchTo = new CommandResult("feedback", UiTab.HOME);
        CommandResult otherCommandResultWithSameTabToSwitchTo = new CommandResult("feedback", UiTab.HOME);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));
        assertTrue(commandResultWithTabToSwitchTo.equals(otherCommandResultWithSameTabToSwitchTo));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));

        // different tabToSwitchTo value -> returns false
        assertFalse(commandResult.equals(commandResultWithTabToSwitchTo));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());

        // different tab to switch to value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", UiTab.HOME).hashCode());
    }
}
