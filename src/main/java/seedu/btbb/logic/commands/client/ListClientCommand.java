package seedu.btbb.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.btbb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.btbb.logic.commands.Command;
import seedu.btbb.logic.commands.CommandResult;
import seedu.btbb.model.Model;

/**
 * Lists all clients in the address book to the user.
 */
public class ListClientCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
