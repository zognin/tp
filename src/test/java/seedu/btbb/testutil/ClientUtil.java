package seedu.btbb.testutil;

import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_ADDRESS;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_EMAIL;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_NAME;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.btbb.logic.commands.client.AddClientCommand;
import seedu.btbb.logic.descriptors.ClientDescriptor;
import seedu.btbb.model.client.Client;

/**
 * A utility class for Client.
 */
public class ClientUtil {
    /**
     * Returns an add command string for adding the {@code client}.
     */
    public static String getAddCommand(Client client) {
        return AddClientCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().toString() + " ");
        sb.append(PREFIX_PHONE + client.getPhone().toString() + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().toString() + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(ClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address).append(" "));
        return sb.toString();
    }
}
