package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes Exit command.
     * @param taskList TaskList object for the duke program
     * @param storage storage object for the duke program
     * @return String to be printed
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            storage.saveData(taskList.getTaskList());
            return Messages.BYE_MESSAGE;
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
