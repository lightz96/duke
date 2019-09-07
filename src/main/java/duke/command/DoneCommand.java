package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int itemNum;

    public DoneCommand(int itemNum) {
        super();
        this.itemNum = itemNum;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        try {
            taskList.getTask(itemNum - 1).completeTask();
            return String.join("\n", Messages.DONE_MESSAGE, Messages.COMMAND_INDENTATION
                    + Messages.COMPLETION_INDENTATION + taskList.getTask(itemNum - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            return Messages.INVALID_SIZE_EXCEPTION;
        }
    }
}
