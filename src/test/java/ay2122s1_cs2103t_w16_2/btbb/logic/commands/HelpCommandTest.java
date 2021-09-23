package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.HelpCommand;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
