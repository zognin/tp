package seedu.address.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.address.exception.CommandException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.descriptors.ClientDescriptor;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

/**
 * Adds a client to the address book.
 */
public class AddClientCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a client to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25.";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLIENT = "This client already exists in the address book";

    private final ClientDescriptor clientDescriptor;

    /**
     * Creates an AddClientCommand to add the specified {@code Client}
     */
    public AddClientCommand(ClientDescriptor clientDescriptor) {
        requireNonNull(clientDescriptor);
        this.clientDescriptor = clientDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Client client = clientDescriptor.toModelType();

        if (model.hasClient(client)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addClient(client);
        return new CommandResult(String.format(MESSAGE_SUCCESS, client));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClientCommand // instanceof handles nulls
                && clientDescriptor.equals(((AddClientCommand) other).clientDescriptor));
    }
}
