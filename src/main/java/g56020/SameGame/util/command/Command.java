package g56020.SameGame.util.command;

public interface Command {
    /**
     * Executes the specified command
     */
    void execute();

    /**
     * Undoes the specified command
     */
    void undo();
}
