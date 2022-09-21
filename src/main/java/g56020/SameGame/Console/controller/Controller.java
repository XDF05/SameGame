package g56020.SameGame.Console.controller;

import g56020.SameGame.Console.view.MyView;
import g56020.SameGame.Console.view.View;
import g56020.SameGame.model.Model;
import g56020.SameGame.model.State;
import g56020.SameGame.util.command.Command;
import g56020.SameGame.util.command.Remove;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final Model game;
    private final View view;

    private final List<Command> commands;
    private final List<Command> undoCommands;

    public Controller(Model game) {
        this.game = game;
        this.view = new MyView(game);

        this.commands = new ArrayList<>();
        this.undoCommands = new ArrayList<>();
    }

    public void play() {
        String[] command;
        game.start(3, 5);
        view.printBlocks();

        while (game.getState() != State.GAME_OVER) {
            command = view.askCommand();
            switch (command[0]) {
                case "remove" -> {
                    int row = Integer.parseInt(command[1]);
                    int col = Integer.parseInt(command[2]);
                    if (this.game.canRemove(row, col)) {
                        Command remove = new Remove(game, row, col);
                        remove.execute();
                        this.commands.add(remove);
                        this.undoCommands.clear();
                    }
                }
                case "undo" -> {
                    try {
                        Command lastCommand = commands.get(commands.size() - 1);
                        commands.remove(lastCommand);
                        undoCommands.add(lastCommand);
                        lastCommand.undo();
                    } catch (Exception e) {
                        System.err.println("No commands left to undo!");
                    }
                }
                case "redo" -> {
                    try {
                        Command lastCommand = undoCommands.get(undoCommands.size() - 1);
                        undoCommands.remove(lastCommand);
                        commands.add(lastCommand);
                        lastCommand.execute();
                    } catch (Exception e) {
                        System.err.println("No commands left to redo!");
                    }
                }
                case "stop" -> {
                    game.giveUp();
                }
            }
        }
    }
}
