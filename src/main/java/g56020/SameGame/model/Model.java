package g56020.SameGame.model;

import g56020.SameGame.util.observer.Observable;

public interface Model extends Observable {
    /**
     * Starts the game
     *
     * @param difficulty chosen difficulty
     * @param size       chosen amount of blocks
     */
    void start(int difficulty, int size);

    /**
     * Whether a block at a given row and column can be removed
     *
     * @param row given row of the block
     * @param col given column of a given block
     * @return whether the block can be removed
     */
    boolean canRemove(int row, int col);

    /**
     * Removes a block at given row and column
     *
     * @param row given row of the block
     * @param col given column of the block
     */
    void removeBlocks(int row, int col);

    /**
     * Gives the current game up
     */
    void giveUp();

    /**
     * updates the values after the undo command has been executed
     *
     * @param previousBlocks    the previous Blocks array
     * @param previousScore     the previous Score
     * @param previousLastScore the previous last move score
     * @param previousNbBlocks  the previous amount of blocks remaining
     */
    void updateValues(Block[][] previousBlocks, int previousScore, int previousLastScore, int previousNbBlocks);

    /**
     * getter for the score
     *
     * @return score
     */
    int getScore();

    /**
     * getter for the latest score
     *
     * @return the latest score
     */
    int getLastScore();

    /**
     * getter for the remaining blocks
     *
     * @return amount of the remaining blocks
     */
    int getRemainingBlocks();

    /**
     * getter for the blocks array
     *
     * @return array which contains every block
     */
    Block[][] getBlocks();

    /**
     * creates a copy of the blocks array
     *
     * @return a copy of the blocks array
     */
    Block[][] getCopyBlocks();

    /**
     * Getter for the current state of the game
     * @return
     */
    State getState();

}
