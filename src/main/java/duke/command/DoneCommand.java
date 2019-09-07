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

    /**
     * Notify the program to exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes Done command.
     * @param taskList TaskList object for the duke program
     * @param storage storage object for the duke program
     * @return String to be printed
     */
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
