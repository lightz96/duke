package duke.task;

import duke.shared.Messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Formats the list of tasks.
     * @param listToDisplay list of tasks to be formatted
     * @return formatted list of tasks
     */
    public String getTasksInString(List<Task> listToDisplay) {
        assert listToDisplay != null;
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < listToDisplay.size(); i++) {
            if (i < listToDisplay.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(listToDisplay.get(i)).append("\n")
                        .append(Messages.COMMAND_INDENTATION);
            } else if (i == listToDisplay.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(listToDisplay.get(i));
            }
        }
        return myStringBuilder.toString();
    }

    /**
     * Adds task to tasklist.
     * @param task task to be added to tasklist
     */
    public void addToTaskList(Task task) {
        assert task != null;
        taskList.add(task);
    }

    /**
     * Provides the number of items in taskList.
     * @return number of items inside taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Removes task from taskList.
     * @param index index of the taskList
     * @return removed task
     * @throws IndexOutOfBoundsException index less than 0 or bigger than the size of taskList
     */
    public Task deleteFromTaskList(int index) throws IndexOutOfBoundsException {
        assert index > 0;
        return taskList.remove(index);
    }

    /**
     * Retrieves task from tasklist.
     * @param index index of the taskList
     * @return requested task
     * @throws IndexOutOfBoundsException index less than 0 or bigger than the size of the tasklist
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        assert index > 0;
        return taskList.get(index);
    }

    /**
     * Provides all tasks within the tasklist.
     * @return a list of tasks within the tasklist
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Finds tasks that contains the keywords stated in the itemToFind.
     * @param itemToFind keywords provided by the user
     * @return list of tasks that matches the keywords
     */
    public List<Task> findTasks(String[] itemToFind) {
        assert itemToFind != null;
        List<Task> matchedTaskList = new ArrayList<>();
        List<String> itemListInString = Arrays.asList(itemToFind);
        for (Task task : taskList) {
            List<String> subNameList = Arrays.asList(task.getName().split("\\s+"));
            if (!Collections.disjoint(itemListInString, subNameList)) {
                matchedTaskList.add(task);
            }
        }
        return matchedTaskList;
    }
}
