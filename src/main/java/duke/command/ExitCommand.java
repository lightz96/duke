package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

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
