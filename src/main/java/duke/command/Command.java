package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Runs the specified command.
     *
     * @param taskList TaskList object for the duke program.
     * @param storage storage object for the duke program.
     * @return String to be printed.
     */
    public abstract String execute(TaskList taskList, Storage storage);

    /**
     * Notifies the program to exit.
     *
     * @return true if it is exitCommand, else false.
     */
    public abstract boolean isExit();
}
