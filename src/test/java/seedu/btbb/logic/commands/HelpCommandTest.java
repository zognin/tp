package seedu.btbb.logic.commands;

import static seedu.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.btbb.logic.commands.general.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.btbb.logic.commands.general.HelpCommand;
import seedu.btbb.model.Model;
import seedu.btbb.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
