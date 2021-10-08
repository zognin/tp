package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Sets tab to switch to if there is one. */
    private UiTab tabToSwitchTo;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * a tab to switch to,
     * and other fields set to their default value.
     *
     * @param feedbackToUser Feedback to show to user.
     * @param tabToSwitchTo Tab to switch to.
     */
    public CommandResult(String feedbackToUser, UiTab tabToSwitchTo) {
        this(feedbackToUser, false, false);
        this.tabToSwitchTo = tabToSwitchTo;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    /**
     * Determines whether the command requires a tab switch.
     *
     * @return True if there is a need to switch tab, false otherwise.
     */
    public boolean isSwitchTab() {
        return tabToSwitchTo != null;
    }

    /**
     * Gets the selected tab to switch to.
     *
     * @return {@code UiTab}.
     */
    public UiTab getTabToSwitchTo() {
        return tabToSwitchTo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        boolean isSameTabToSwitchTo = (tabToSwitchTo == null || otherCommandResult.tabToSwitchTo == null)
                ? tabToSwitchTo == otherCommandResult.tabToSwitchTo
                : tabToSwitchTo.equals(otherCommandResult.tabToSwitchTo);

        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && isSameTabToSwitchTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, tabToSwitchTo);
    }
}
