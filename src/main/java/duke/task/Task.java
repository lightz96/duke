package duke.task;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Provides constructor that will be used for entered command.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        //Used when user entered command
        this.name = name;
        this.isDone = false;
    }

    /**
     * Provides constructor that will be used for loading data from text.
     *
     * @param name name of the task.
     * @param isDone is the status of the task.
     */
    public Task(String name, boolean isDone) {
        //Used when program loads data from text
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Change status icon to tick.
     */
    public void completeTask() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "O" : "X";
    }

    public abstract char getShortForm();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getName();
    }
}