package ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe;

import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.LogsCenter;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class ListRecipeCommand extends Command {
    public static final String COMMAND_WORD = "list-r";

    public static final String MESSAGE_SUCCESS = "Listed all recipes";

    private static final Logger logger = LogsCenter.getLogger(JsonUtil.class);

    @Override
    public CommandResult execute(Model model) {
        logger.info("Executing " + ListRecipeCommand.class.getSimpleName());

        requireNonNull(model);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(MESSAGE_SUCCESS, UiTab.HOME);
    }
}
