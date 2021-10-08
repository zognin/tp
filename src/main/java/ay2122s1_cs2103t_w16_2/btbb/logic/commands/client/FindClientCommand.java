package ay2122s1_cs2103t_w16_2.btbb.logic.commands.client;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.predicate.ClientPredicateCollection;

/**
 * Finds and lists all clients in the address book whose name, phone number,
 * email or address matches the provided parameters.
 */
public class FindClientCommand extends Command {
    public static final String COMMAND_WORD = "find-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds clients by "
            + "name, phone number, email or address fields. \n"
            + "Parameters (at least one must be provided): "
            + "[" + PREFIX_CLIENT_NAME + "NAME] "
            + "[" + PREFIX_CLIENT_PHONE + "PHONE] "
            + "[" + PREFIX_CLIENT_EMAIL + "EMAIL] "
            + "[" + PREFIX_CLIENT_ADDRESS + "ADDRESS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CLIENT_NAME + "alice";

    private final ClientPredicateCollection predicateCollection;

    public FindClientCommand(ClientPredicateCollection predicateCollection) {
        this.predicateCollection = predicateCollection;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(predicateCollection);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientCommand // instanceof handles nulls
                && predicateCollection.equals(((FindClientCommand) other).predicateCollection)); // state check
    }
}
