package duke.command;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

public class EventCommand extends AddCommand {
    private String[] commands;

    public EventCommand(String[] commands) {
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
     * Executes Event command.
     * @param taskList TaskList object for the duke program
     * @param storage storage object for the duke program
     * @return String to be printed
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            String[] args = GetArgumentsUtil.getTwoCommandArgs(1, "/at", commands);
            Task eventTask = new Event(args[0], args[1]);
            taskList.addToTaskList(eventTask);
            return String.join("\n", Messages.ADDED_TASK_MESSAGE, Messages.COMMAND_INDENTATION
                    + Messages.COMPLETION_INDENTATION + eventTask.toString(),
                    String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
