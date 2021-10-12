package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new DeleteOrderCommand object
 */
public class DeleteOrderCommandParser implements Parser<DeleteOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOrderCommand
     * and returns a DeleteOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
