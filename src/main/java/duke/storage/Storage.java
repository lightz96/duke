package duke.storage;

import duke.exception.DukeException;
import duke.shared.GetArgumentsUtil;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Loads and save data from text file.
 */
public class Storage {

    private String filePath;
    private List<Task> taskList;

    public static final String DATETIME_PATTERN = "dd MMMM yyyy, hh:mm a";

    public static final int DONE_INDEX = 2;
    public static final int TASK_INDEX = 0;
    public static final int START_ARGUMENTS_INDEX = 4;

    /**
     * Provides constructor for storage.
     *
     * @param filePath file path to load our data from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and load data from file.
     *
     * @return list of past tasks saved in the text file.
     * @throws IOException throws by {@link #readFile()}.
     * @throws DukeException throws by {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])}.
     */
    public List<Task> load() throws IOException, DukeException {
        taskList = new ArrayList<>();
        readFile();
        return taskList;
    }

    /**
     * Reads and decode the text in the text file line-by-line.
     *
     * @throws IOException if file cannot be found / file cannot be opened.
     * @throws DukeException throws by {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])}.
     */
    public void readFile() throws IOException, DukeException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            while ((line = br.readLine()) != null) {
                decodeLine(line);
            }
        } finally {
            br.close();
        }
    }

    /**
     * Decodes the line and adds to the tasklist.
     *
     * @param line line read from the text file.
     * @throws DukeException throws by {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])}.
     */
    public void decodeLine(String line) throws DukeException {
        String[] inputLines = line.split("\\s+");
        Task task = null;
        if (inputLines[TASK_INDEX].equals("T")) {
            task = toDoParser(inputLines);
        } else if (inputLines[TASK_INDEX].equals("E")) {
            task = eventParser(inputLines);
        } else if (inputLines[TASK_INDEX].equals("D")) {
            task = deadlineParser(inputLines);
        }
        taskList.add(task);
    }

    /**
     * Converts the decoded line to ToDo object.
     *
     * @param inputLines line from the text file.
     * @return converted todo object.
     */
    public Task toDoParser(String[] inputLines) {
        assert inputLines.length > 4 : "inputLines should contain more than 4 items";

        Task toDoTask = null;
        String taskName = String.join(" ", Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX,
                inputLines.length));
        if (inputLines[DONE_INDEX].equals("1")) {
            toDoTask = new Todo(taskName, true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            toDoTask = new Todo(taskName, false);
        }

        assert toDoTask != null;
        return toDoTask;
    }

    /**
     * Converts the decoded line to Event object.
     *
     * @param inputLines line from the text file.
     * @return converted event object.
     * @throws DukeException throws by {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])}.
     */
    public Task eventParser(String[] inputLines) throws DukeException {
        assert inputLines.length > 4 : "inputLines should contain more than 4 items";

        Task eventTask = null;
        String[] args = GetArgumentsUtil.getTwoCommandArgs(0,"|",
                Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX, inputLines.length));
        if (inputLines[DONE_INDEX].equals("1")) {
            eventTask = new Event(args[0], args[1], true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            eventTask = new Event(args[0], args[1], false);
        }

        assert eventTask != null;
        return eventTask;
    }

    /**
     * Converts the decoded line to Deadline Object.
     *
     * @param inputLines line from the text file.
     * @return converted deadline object.
     * @throws DukeException throws by {@link GetArgumentsUtil#getTwoCommandArgs(int, String, String[])}.
     */
    public Task deadlineParser(String[] inputLines) throws DukeException {
        assert inputLines.length > 4 : "inputLines should contain more than 4 items";

        Task deadlineTask = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        String[] args = GetArgumentsUtil.getTwoCommandArgs(0, "|",
                Arrays.copyOfRange(inputLines, START_ARGUMENTS_INDEX, inputLines.length));
        LocalDateTime dateTime = LocalDateTime.parse(args[1], formatter);
        if (inputLines[DONE_INDEX].equals("1")) {
            deadlineTask = new Deadline(args[0], dateTime, true);
        } else if (inputLines[DONE_INDEX].equals("0")) {
            deadlineTask = new Deadline(args[0], dateTime, false);
        }

        assert deadlineTask != null;
        return deadlineTask;
    }

    /**
     * Encodes the task and save it to the text file.
     *
     * @param taskList latest list of tasks.
     * @throws IOException throws by {@link #writeToFile(String)}.
     */
    public void saveData(List<Task> taskList) throws IOException {
        assert taskList != null;

        StringBuilder myStringBuilder = new StringBuilder();
        for (Task task : taskList) {
            if (task instanceof Todo) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append("\n");
            } else if (task instanceof Event) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append(" | ").append(((Event) task).getTiming()).append("\n");
            } else if (task instanceof Deadline) {
                myStringBuilder.append(task.getShortForm()).append(" | ").append(task.getStatus() ? 1 : 0).append(" | ")
                        .append(task.getName()).append(" | ").append(((Deadline) task).getFormattedDateTime())
                        .append("\n");
            }
        }
        myStringBuilder.deleteCharAt(myStringBuilder.length() - 1);
        writeToFile(myStringBuilder.toString());
    }

    /**
     * Writes text to file.
     *
     * @param linesToWrite string to be written to the file.
     * @throws IOException if file cannot be opened.
     */
    public void writeToFile(String linesToWrite) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.append(linesToWrite);
        bufferedWriter.close();
    }
}
