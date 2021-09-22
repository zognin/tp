package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_PHONE;

import seedu.address.exception.ParseException;
import seedu.address.logic.commands.client.AddCommand;
import seedu.address.logic.descriptors.PersonDescriptor;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!ParserUtil.areAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        PersonDescriptor personDescriptor = new PersonDescriptor();
        personDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        personDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        personDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        personDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));

        return new AddCommand(personDescriptor);
    }

}
