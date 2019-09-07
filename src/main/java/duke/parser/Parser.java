package duke.parser;

import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.shared.Messages;
import java.util.Arrays;

/**
 * Parses command.
 */
public class Parser {
    /**
     * Converts the command into command object.
     *
     * @param command command inputted by the user.
     * @return converted command object.
     * @throws DukeException throws by {@link #findCommand(String[])}.
     * @throws NumberFormatException throws by {@link #findCommand(String[])}.
     */
    public static Command parse(String command) throws DukeException, NumberFormatException {
        String[] commandArr = command.split("\\s+");
        return findCommand(commandArr);
    }

    /**
     * Returns the correct command object based on user inputted command.
     *
     * @param commands is the last command entered by the user.
     * @throws DukeException if user inputted an invalid command.
     * @throws NumberFormatException if user inputted an invalid description for done and delete command.
     */
    public static Command findCommand(String[] commands) throws DukeException, NumberFormatException {
        if (commands[0].equals("bye")) {
            if (commands.length == 1) {
                return new ExitCommand();
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("list")) {
            if (commands.length == 1) {
                return new ListCommand();
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("done")) {
            if (commands.length == 2) {
                return new DoneCommand(Integer.parseInt(commands[1]));
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "done"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("todo")) {
            if (commands.length > 1) {
                return new ToDoCommand(commands);
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "todo"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("deadline")) {
            if (commands.length > 1) {
                return new DeadlineCommand(commands);
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "deadline"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("event")) {
            if (commands.length > 1) {
                return new EventCommand(commands);
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "event"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        } else if (commands[0].equals("delete")) {
            try {
                if (commands.length == 2) {
                    return new DeleteCommand(Integer.parseInt(commands[1]));
                } else if (commands.length == 1) {
                    throw new DukeException(String.format(Messages.DESCRIPTION_MISSING_EXCEPTION, "delete"));
                } else {
                    throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "delete",
                        "number"));
            }
        } else if (commands[0].equals("find")) {
            if (commands.length > 1) {
                return new FindCommand(Arrays.copyOfRange(commands, 1, commands.length));
            } else if (commands.length == 1) {
                throw new DukeException(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "find"));
            } else {
                throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
            }
        }
        throw new DukeException(Messages.UNKNOWN_COMMAND_EXCEPTION);
    }
}
