package g56020.SameGame.model;

import g56020.SameGame.util.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for the SameGame application
 */
public class Game implements Model {
    private State state;

    private Board board;
    private int score;
    private int lastScore;

    private final List<Observer> observers;

    /**
     * Constructor for the game
     */
    public Game() {
        this.state = State.NOT_STARTED;
        this.observers = new ArrayList<>();
    }

    @Override
    public void start(int difficulty, int size) {
        this.state = State.WAIT_INPUT;
        this.board = new Board(difficulty, size);
        this.score = 0;
    }

    void start(Board board) {
        this.state = State.WAIT_INPUT;
        this.board = board;
        this.score = 0;
    }

    @Override
    public boolean canRemove(int row, int col) {
        if (this.state != State.GAME_OVER && !this.board.canRemove(row, col)) {
            notifyObservers("INVALID_BLOCK");
            return false;
        }
        return true;
    }

    @Override
    public void removeBlocks(int x, int y) {
        if (this.state == State.WAIT_INPUT) {
            int nbRemovedBlocks = -1;
            try {
                nbRemovedBlocks = this.board.removeBlocks(x, y);
                isGameOver();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            if (nbRemovedBlocks != -1) {
                score += nbRemovedBlocks * (nbRemovedBlocks - 1);
                lastScore = nbRemovedBlocks * (nbRemovedBlocks - 1);
                int[] scores = {score, lastScore, this.getRemainingBlocks()};
                notifyObservers(scores);
                if (this.state == State.GAME_OVER) {
                    notifyObservers(board.isWinner());
                }
            }
        }
    }

    @Override
    public void giveUp() {
        this.state = State.GAME_OVER;
        notifyObservers(false);
    }

    @Override
    public void updateValues(Block[][] previousBlocks, int previousScore, int previousLastScore, int previousNbBlocks) {
        this.board.setBlocks(previousBlocks);
        this.board.setRemainingBlocks(previousNbBlocks);

        this.score = previousScore;
        this.lastScore = previousLastScore;

        int[] scores = {score, lastScore, this.getRemainingBlocks()};
        this.notifyObservers(scores);
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getLastScore() {
        return this.lastScore;
    }

    @Override
    public int getRemainingBlocks() {
        return this.board.getRemainingBlocks();
    }

    @Override
    public Block[][] getBlocks() {
        return this.board.getBlocks();
    }

    @Override
    public Block[][] getCopyBlocks() {
        Block[][] blocks = this.getBlocks();
        Block[][] copy = new Block[blocks.length][blocks[0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                copy[i][j] = new Block(blocks[i][j]);
            }
        }
        return copy;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(Object args) {
        observers.forEach(observer -> observer.update(this, this.state, args));
    }

    /**
     * verifies whether the game is over
     */
    private void isGameOver() {
        if (board.isGameOver()) {
            this.state = State.GAME_OVER;
        }
    }
}
