package duke.command;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends AddCommand {

    private static final String DATETIME_PATTERN = "dd/MM/yyyy HHmm";
    private String[] commands;

    public DeadlineCommand(String[] commands) {
        this.commands = commands;
    }

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }


    /**
     * Executes Deadline command.
     * @param taskList TaskList object for the duke program
     * @param storage storage object for the duke program
     * @return String to be printed
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/by", commands);
            LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
            Task deadlineTask = new Deadline(args[0], dateTime);
            taskList.addToTaskList(deadlineTask);
            String message = String.join("\n", Messages.ADDED_TASK_MESSAGE,
                    Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + deadlineTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
            return message;
        } catch (DateTimeParseException e) {
            return Messages.DATETIME_PARSE_EXCEPTION;
        } catch (DukeException e1) {
            return e1.getMessage();
        }
    }
}
