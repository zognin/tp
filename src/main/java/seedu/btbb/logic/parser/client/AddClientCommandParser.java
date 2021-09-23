package seedu.btbb.logic.parser.client;

import static seedu.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_ADDRESS;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_EMAIL;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_NAME;
import static seedu.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.btbb.exception.ParseException;
import seedu.btbb.logic.commands.client.AddClientCommand;
import seedu.btbb.logic.descriptors.ClientDescriptor;
import seedu.btbb.logic.parser.Parser;
import seedu.btbb.logic.parser.util.ArgumentMultimap;
import seedu.btbb.logic.parser.util.ArgumentTokenizer;
import seedu.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new AddClientCommand object
 */
public class AddClientCommandParser implements Parser<AddClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        ClientDescriptor clientDescriptor = new ClientDescriptor();
        clientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        clientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        clientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        clientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));

        return new AddClientCommand(clientDescriptor);
    }
}
