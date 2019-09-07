package duke.command;

import duke.shared.GetArgumentsUtil;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.Arrays;

public class ToDoCommand extends AddCommand {
    private String[] commands;
    private static final int TODO_ARGUMENT_START_INDEX = 1;

    public ToDoCommand(String[] commands) {
        this.commands = commands;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null : "tasklist cannot be null";
        assert storage != null : "storage cannot be null";

        String arg = GetArgumentsUtil.concatStrings(Arrays.copyOfRange(commands, TODO_ARGUMENT_START_INDEX,
                commands.length));
        Task toDoTask = new Todo(arg);
        taskList.addToTaskList(toDoTask);
        return String.join("\n", Messages.ADDED_TASK_MESSAGE,
                Messages.COMMAND_INDENTATION + Messages.COMPLETION_INDENTATION + toDoTask.toString(),
                String.format(Messages.LIST_SIZE_FORMAT, taskList.getSize()));
    }
}
