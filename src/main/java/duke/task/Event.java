package duke.task;

public class Event extends Task {
    private char shortForm = 'E';
    protected String timing;

    /**
     * Provides constructor that will be used for entered command.
     *
     * @param name name of the event task.
     * @param timing datetime/location of the event task.
     */
    public Event(String name, String timing) {
        //Used when user entered command
        super(name);
        this.timing = timing;
    }

    /**
     * Provides constructor that will be used for loading data from text.
     *
     * @param name name of the event task.
     * @param timing datetime/location of the event task.
     * @param isDone is the status of the task.
     */
    public Event(String name, String timing, boolean isDone) {
        //Used when program loads data from text
        super(name, isDone);
        this.timing = timing;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public char getShortForm() {
        return shortForm;
    }

    @Override
    public String toString() {
        return "[" + getShortForm() + "]" + super.toString() + " (at: " + timing + ")";
    }
}
