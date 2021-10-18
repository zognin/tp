package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Lists all clients in the address book to the user.
 */
public class ListClientCommand extends Command {
    public static final String COMMAND_WORD = "list-c";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    @Override
    public CommandResult execute(Model model) {
        logger.info("Executing " + ListClientCommand.class.getSimpleName());

        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS, UiTab.HOME);
    }
}
