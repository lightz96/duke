package duke.command;

public abstract class AddCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }
}
