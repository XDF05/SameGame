package g56020.SameGame.model;

/**
 * Representation of the board for the model
 */
public class Board {
    private Block[][] blocks;
    private int remainingBlocks;
    private char[] colors;

    /**
     * Constructor for the board
     *
     * @param difficulty chosen difficulty by the user [3-5]
     * @param size       chosen amount of blocks [5-20]
     */
    public Board(int difficulty, int size) {
        blocks = new Block[size][size];
        remainingBlocks = size * size;

        switch (difficulty) {
            case 3 -> colors = new char[]{'r', 'g', 'b'};           //red, green, blue
            case 4 -> colors = new char[]{'r', 'g', 'b', 'y'};      //red, green, blue, yellow
            case 5 -> colors = new char[]{'r', 'g', 'b', 'y', 'p'}; //red, green, blue, yellow, purple
        }

        /*
        Initializes the board with randomly picked colors
        among the possible colors according to the chosen difficulty
         */
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[row].length; col++) {
                char color = randomColor(difficulty);
                blocks[row][col] = new Block(color);
            }
        }
    }

    Board(Block[][] blocks) {
        this.blocks = blocks;
        remainingBlocks = blocks.length * blocks.length;
        this.blocks = blocks;
    }

    /*
    picks a random color from the available colors
    (which depends on the chosen difficulty)
     */
    private char randomColor(int difficulty) {
        int randInt = (int) (Math.random() * difficulty);
        return colors[randInt];
    }

    /**
     * verifies whether the game is over
     *
     * @return is game over
     */
    public boolean isGameOver() {
        //  iterate over columns
        for (int col = 0; col < blocks[0].length; col++) {
            // iterate over rows
            for (int row = blocks.length - 1; row >= 0; row--) {
                char nColor = blocks[row][col].getColor();
                //  if the column is empty, go to next column
                if (nColor == ' ')
                    break;
                else {
                    //  Check above and right
                    if (row - 1 >= 0 &&
                            blocks[row - 1][col].getColor() == nColor)
                        return false;
                    else if (col + 1 < blocks[0].length &&
                            blocks[row][col + 1].getColor() == nColor)
                        return false;
                }
            }
        }
        //  No adjacent blocks with the same color found
        return true;

    }

    /**
     * Once the game is over, verifies if the player won or lost the game
     *
     * @return is winner
     */
    public boolean isWinner() {
        for (int col = 0; col < blocks[0].length; col++) {
            for (int row = blocks.length - 1; row >= 0; row--) {
                char nColor = blocks[row][col].getColor();
                if (nColor != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * verifies whether a block can be removed according to a given position
     *
     * @param row given row of the block
     * @param col given column of the block
     * @return can be removed
     */
    public boolean canRemove(int row, int col) {
        char nColor = blocks[row][col].getColor();
        if (nColor != ' ') {
            return ((row - 1 >= 0 && (blocks[row - 1][col].getColor() == nColor)) ||
                    (row + 1 < blocks.length && (blocks[row + 1][col].getColor() == nColor)) ||
                    (col - 1 >= 0 && (blocks[row][col - 1].getColor() == nColor)) ||
                    (col + 1 < blocks[0].length && (blocks[row][col + 1].getColor() == nColor)));
        }
        //the block has already been removed
        return false;
    }

    /**
     * Removes a block at a given position if it's allowed
     *
     * @param row given row
     * @param col given col
     * @return amount of blocks removed
     */
    public int removeBlocks(int row, int col) {
        //  Make sure that the row is valid
        if (row < 0 || row > blocks.length - 1) {
            throw new IllegalArgumentException("Invalid row");
        }
        //  Make sure that the column is valid
        if (col < 0 || col > blocks[row].length - 1) {
            throw new IllegalArgumentException("Invalid column");
        }
        //  Can't delete already removed blocks
        char nColor = blocks[row][col].getColor();
        if (nColor == ' ') {
            throw new IllegalArgumentException("Block already deleted!");
        }
        //	First check if there are any of the adjacent sides
        //  with the same color
        int nCount = -1;
        if (canRemove(row, col)) {
            //  Then call the recursive function to eliminate all
            //  other touching blocks with same color
            blocks[row][col].setColor(' ');
            nCount = 1;
            //  Recursive call for up
            nCount +=
                    removeNeighborBlocks(row - 1, col, nColor, Direction.DOWN);
            //  Recursive call for down
            nCount +=
                    removeNeighborBlocks(row + 1, col, nColor, Direction.UP);
            //  Recursive call for left
            nCount +=
                    removeNeighborBlocks(row, col - 1, nColor, Direction.RIGHT);
            //  Recursive call for right
            nCount +=
                    removeNeighborBlocks(row, col + 1, nColor, Direction.LEFT);
            //  Finally, compact the board
            CompactBoard();
            //  Remove the count from the number remaining
            remainingBlocks -= nCount;
        }
        //  Return the total number of blocks deleted
        return nCount;
    }

    /**
     * removes the neighbor blocks of a given block according to its position and a direction
     *
     * @param row       given row of the block
     * @param col       given col of the block
     * @param color     given color of the initial block
     * @param direction given direction to go towards
     * @return amount of neighbor blocks removed with the same color in the given direction
     */
    private int removeNeighborBlocks(int row, int col, int color, Direction direction) {
        //  Check if it is on the board
        if (row < 0 || row > blocks.length - 1) {
            return 0;
        }
        if (col < 0 || col > blocks[row].length - 1) {
            return 0;
        }
        //  Check if it has the same color
        if (blocks[row][col].getColor() != color) {
            return 0;
        }
        int nCount = 1;
        blocks[row][col].setColor(' ');
        //  If we weren't told to not go back up, check up
        if (direction != Direction.UP) {
            nCount +=
                    removeNeighborBlocks(row - 1, col, color, Direction.DOWN);
        }
        //  If we weren't told to not go back down, check down
        if (direction != Direction.DOWN) {
            nCount +=
                    removeNeighborBlocks(row + 1, col, color, Direction.UP);
        }
        //  If we weren't told to not go back left, check left
        if (direction != Direction.LEFT) {
            nCount +=
                    removeNeighborBlocks(row, col - 1, color, Direction.RIGHT);
        }
        //  If we weren't told to not go back right, check right
        if (direction != Direction.RIGHT) {
            nCount +=
                    removeNeighborBlocks(row, col + 1, color, Direction.LEFT);
        }
        //  Return the total number of blocks deleted
        return nCount;
    }

    /**
     * Compacts the board,
     * removed empty columns
     * and pushes every block downwards
     */
    private void CompactBoard() {
        //  First move everything down
        for (int col = 0; col < blocks[0].length; col++) {
            int nNextEmptyRow = blocks.length - 1;
            int nNextOccupiedRow = nNextEmptyRow;
            while (nNextOccupiedRow >= 0 && nNextEmptyRow >= 0) {
                //  First find the next empty row
                while (nNextEmptyRow >= 0 &&
                        blocks[nNextEmptyRow][col].getColor() != ' ') {
                    nNextEmptyRow--;
                }
                if (nNextEmptyRow >= 0) {
                    //  Then find the next occupied row from the next empty row
                    nNextOccupiedRow = nNextEmptyRow - 1;
                    while (nNextOccupiedRow >= 0 &&
                            blocks[nNextOccupiedRow][col].getColor() == ' ') {
                        nNextOccupiedRow--;
                    }
                    if (nNextOccupiedRow >= 0) {
                        //  Now move the block from occupied to empty
                        blocks[nNextEmptyRow][col].setColor(
                                blocks[nNextOccupiedRow][col].getColor()
                        );
                        blocks[nNextOccupiedRow][col].setColor(' ');
                    }
                }
            }
        }
        //  Then move everything from right to left
        int nNextEmptyCol = 0;
        int nNextOccupiedCol = nNextEmptyCol;
        while (nNextEmptyCol < blocks[0].length && nNextOccupiedCol < blocks[0].length - 1) {
            //  First find the next empty column
            while (nNextEmptyCol < blocks[0].length &&
                    blocks[blocks.length - 1][nNextEmptyCol].getColor() != ' ')
                nNextEmptyCol++;
            if (nNextEmptyCol < blocks[0].length) {
                //  Then find the next column with something in it
                nNextOccupiedCol = nNextEmptyCol + 1;
                while (nNextOccupiedCol < blocks[0].length - 1 &&
                        blocks[blocks.length - 1][nNextOccupiedCol].getColor() == ' ')
                    nNextOccupiedCol++;
                if (nNextOccupiedCol < blocks[0].length) {
                    //  Move entire column to the left
                    for (Block[] block : blocks) {
                        block[nNextEmptyCol].setColor(
                                block[nNextOccupiedCol].getColor()
                        );
                        block[nNextOccupiedCol].setColor(' ');
                    }
                }
            }
        }
    }

    /**
     * Getter for the total remaining blocks
     *
     * @return amount of blocks remaining
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Setter for the remaining blocks
     *
     * @param remainingBlocks remaining blocks to be set
     */
    public void setRemainingBlocks(int remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * getter for the blocks array
     *
     * @return 2d array of blocks
     */
    public Block[][] getBlocks() {
        return this.blocks;
    }

    /**
     * Setter for the blocks array (used with undo-redo)
     *
     * @param blocks given 2d array of blocks
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }
}
