package duke.command;

import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;

public class FindCommand extends Command {

    private String[] itemsToFind;

    public FindCommand(String[] itemToFind) {
        this.itemsToFind = itemToFind;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        List<Task> taskMatchedList = taskList.findTasks(itemsToFind);
        String formattedTasks = taskList.getTasksInString(taskMatchedList);
        return String.join("\n", Messages.FIND_TASK_MESSAGE, Messages.COMMAND_INDENTATION
                + formattedTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
