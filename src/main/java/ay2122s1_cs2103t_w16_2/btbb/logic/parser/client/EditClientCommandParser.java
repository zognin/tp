package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.EditClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditClientCommandParser implements Parser<EditClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns an EditClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE),
                    pe);
        }

        ClientDescriptor editClientDescriptor = new ClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editClientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditClientCommand(index, editClientDescriptor);
    }
}
