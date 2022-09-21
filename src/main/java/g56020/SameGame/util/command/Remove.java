package g56020.SameGame.util.command;

import g56020.SameGame.model.Block;
import g56020.SameGame.model.Model;

import java.util.ArrayList;
import java.util.List;

public class Remove implements Command {
    private final Model game;
    private Block[][] blocksHistory;
    private List<Integer> scoreHistory;

    private final int row;
    private final int col;

    /**
     * Constructor for the remove command
     *
     * @param game current instance of the game
     * @param row  given row of a block
     * @param col  given column of a block
     */
    public Remove(Model game, int row, int col) {
        this.game = game;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        saveHistory();
        this.game.removeBlocks(row, col);
    }

    @Override
    public void undo() {
        this.game.updateValues(blocksHistory, scoreHistory.get(0), scoreHistory.get(1), scoreHistory.get(2));
    }

    private void saveHistory() {
        this.blocksHistory = game.getCopyBlocks();
        this.scoreHistory = new ArrayList<>();
        this.scoreHistory.add(game.getScore());
        this.scoreHistory.add(game.getLastScore());
        this.scoreHistory.add(game.getRemainingBlocks());
    }
}
