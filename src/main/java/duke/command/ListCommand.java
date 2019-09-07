package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes List command.
     * @param taskList TaskList object for the duke program
     * @param storage storage object for the duke program
     * @return String to be printed
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        String formattedString = taskList.getTasksInString(taskList.getTaskList());
        String message = String.join("\n", Messages.LIST_MESSAGE,
                Messages.COMMAND_INDENTATION + formattedString);
        return message;
    }
}
