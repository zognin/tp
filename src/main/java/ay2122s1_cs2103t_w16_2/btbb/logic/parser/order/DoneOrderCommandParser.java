package ay2122s1_cs2103t_w16_2.btbb.logic.parser.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DoneOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new DoneOrderCommand object.
 */
public class DoneOrderCommandParser implements Parser<DoneOrderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DoneOrderCommand
     * and returns an DoneOrderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneOrderCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneOrderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneOrderCommand.MESSAGE_USAGE), pe);
        }
    }
}
