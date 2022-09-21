package g56020.SameGame.JavaFX.controller;

import g56020.SameGame.JavaFX.view.MyView;
import g56020.SameGame.model.Game;
import g56020.SameGame.model.Model;
import g56020.SameGame.util.command.Command;
import g56020.SameGame.util.command.Remove;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the graphical version of the SameGame application
 */
public class Controller {
    private Model game;
    private MyView view;

    private Stage stage;

    private List<Command> commands;
    private List<Command> undoCommands;

    /**
     * Constructor for the controller
     *
     * @param game current instance of the game
     */
    public Controller(Model game) {
        this.commands = new ArrayList<>();
        this.undoCommands = new ArrayList<>();

        this.game = game;
        this.view = new MyView(this, this.game);
    }

    /**
     * Starts the game
     *
     * @param stage primary stage
     */
    public void play(Stage stage) {
        this.stage = stage;
        this.view.start(stage);
    }

    /**
     * Creates and executes a new Remove command
     *
     * @param row given row used for the execute command
     * @param col given col used for the execute command
     */
    public void execute(int row, int col) {
        if (this.game.canRemove(row, col)) {
            Command remove = new Remove(game, row, col);
            remove.execute();
            this.commands.add(remove);
            this.undoCommands.clear();
        }
    }

    /**
     * Undoes the last executed command
     */
    public void undo() {
        try {
            Command lastCommand = commands.get(commands.size() - 1);
            commands.remove(lastCommand);
            undoCommands.add(lastCommand);
            lastCommand.undo();
        } catch (Exception e) {
            System.err.println("No commands left to undo!");
        }
    }

    /**
     * Redoes the last undone command (must exist)
     */
    public void redo() {
        try {
            Command lastCommand = undoCommands.get(undoCommands.size() - 1);
            undoCommands.remove(lastCommand);
            commands.add(lastCommand);
            lastCommand.execute();
        } catch (Exception e) {
            System.err.println("No commands left to redo!");
        }
    }

    /**
     * Allows the user to give up the current game.
     */
    public void stop() {
        this.game.giveUp();
    }

    /**
     * Allows the user to restart a game after the current is done (or has been given up).
     */
    public void restart() {
        this.game = new Game();
        this.view = new MyView(this, this.game);
        this.commands = new ArrayList<>();
        this.undoCommands = new ArrayList<>();
        play(stage);
    }
}
