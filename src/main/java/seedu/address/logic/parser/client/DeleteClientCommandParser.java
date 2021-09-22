package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.exception.ParseException;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new DeleteClientCommand object
 */
public class DeleteClientCommandParser implements Parser<DeleteClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteClientCommand
     * and returns a DeleteClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteClientCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE), pe);
        }
    }

}
