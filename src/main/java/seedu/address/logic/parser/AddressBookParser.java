package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.exception.ParseException;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.booking.AddBookingCommand;
import seedu.address.logic.commands.client.AddClientCommand;
import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.commands.client.FindClientCommand;
import seedu.address.logic.commands.client.ListClientCommand;
import seedu.address.logic.commands.general.ExitCommand;
import seedu.address.logic.commands.general.HelpCommand;
import seedu.address.logic.parser.booking.AddBookingCommandParser;
import seedu.address.logic.parser.client.AddClientCommandParser;
import seedu.address.logic.parser.client.DeleteClientCommandParser;
import seedu.address.logic.parser.client.FindClientCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case AddBookingCommand.COMMAND_WORD:
            return new AddBookingCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case ListClientCommand.COMMAND_WORD:
            return new ListClientCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
