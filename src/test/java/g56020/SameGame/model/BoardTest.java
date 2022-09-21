package g56020.SameGame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    /**
     * Creates an array with:
     * <p>
     * red blocks on first and 4th rows
     * <p>
     * green blocks on the second and last rows
     * <p>
     * blue blocks on the third row
     * <p>
     *
     * @return blocks array
     */
    private Block[][] createBlocksRow() {
        Block[][] blocks = new Block[5][5];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                switch (row) {
                    case 0, 3 -> blocks[row][col] = new Block('r');
                    case 1, 4 -> blocks[row][col] = new Block('g');
                    case 2 -> blocks[row][col] = new Block('b');
                }
            }
        }
        return blocks;
    }

    /**
     * Creates an array with:
     * <p>
     * red blocks on first and 4th columns
     * <p>
     * green blocks on the second and last columns
     * <p>
     * blue blocks on the third column
     * <p>
     *
     * @return blocks array
     */
    private Block[][] createBlocksColumn() {
        Block[][] blocks = new Block[5][5];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                switch (col) {
                    case 0, 3 -> blocks[row][col] = new Block('r');
                    case 1, 4 -> blocks[row][col] = new Block('g');
                    case 2 -> blocks[row][col] = new Block('b');
                }
            }
        }
        return blocks;
    }

    /**
     * Creates a blocks array
     * and deletes each block
     * <p>
     * verify, if game is over
     */
    @Test
    void isGameOver_empty() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);
        for (int i = 0; i < 5; i++) {
            board.removeBlocks(i, 0);
        }
        assertTrue(board.isGameOver());
    }

    /**
     * Creates a blocks array
     * and deletes each block except one
     * <p>
     * verify, if game is over
     */
    @Test
    void isGameOver_no_adjacent() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);
        blocks[4][4].setColor('b');
        for (int i = 0; i < 5; i++) {
            board.removeBlocks(i, 0);
        }
        assertTrue(board.isGameOver());
    }

    /**
     * Verifies whether the game is won after removing every single block
     */
    @Test
    void isWinner_true() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);
        for (int i = 0; i < 5; i++) {
            board.removeBlocks(i, 0);
        }
        assertTrue(board.isWinner());
    }

    /**
     * verifies whether the game is won once the game is over with one block left
     */
    @Test
    void isWinner_false() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);
        blocks[4][4].setColor('b');
        for (int i = 0; i < 5; i++) {
            board.removeBlocks(i, 0);
        }
        assertFalse(board.isWinner());
    }

    /**
     * verifies whether the first row can be removed
     */
    @Test
    void canRemove_first_row() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);

        assertTrue(board.canRemove(0, 0));
    }

    /**
     * verifies whether the first block (with no adjacent blocks that have the same color)
     */
    @Test
    void canRemove_false() {
        Block[][] blocks = createBlocksRow();
        blocks[0][0].setColor('b');
        Board board = new Board(blocks);

        assertFalse(board.canRemove(0, 0));
    }

    /**
     * calls the createBlocks method and removes the first block
     * verifies whether the first row has successfully been removed
     */
    @Test
    void removeBlocks() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);

        boolean isRemoved = true;
        board.removeBlocks(0, 0);
        for (int col = 0; col < blocks[0].length; col++) {
            if (blocks[0][col].getColor() != ' ') {
                isRemoved = false;
                break;
            }
        }
        assertTrue(isRemoved);
    }

    /**
     * calls the createBlocks method and removes the last row
     * verifies whether the rows above have been shifted
     */
    @Test
    void removeBlocks_shifting_rows() {
        Block[][] blocks = createBlocksRow();
        Board board = new Board(blocks);

        char[] rowsColor = new char[blocks.length];
        board.removeBlocks(4, 0);

        for (int row = 0; row < blocks.length; row++) {
            rowsColor[row] = blocks[row][0].getColor();
        }
        char[] expectedResult = {' ', 'r', 'g', 'b', 'r'};

        assertArrayEquals(expectedResult, rowsColor);
    }

    /**
     * calls the createBlocks method and removes the first column
     * verifies whether the columns right to the removed column have been shifted
     */
    @Test
    void removeBlocks_shifting_columns() {
        Block[][] blocks = createBlocksColumn();
        Board board = new Board(blocks);

        char[] columnsColor = new char[blocks.length];
        board.removeBlocks(0, 0);
        
        for (int col = 0; col < blocks[0].length; col++) {
            columnsColor[col] = blocks[0][col].getColor();
        }
        char[] expectedResult = {'g', 'b', 'r', 'g', ' '};

        assertArrayEquals(expectedResult, columnsColor);
    }
}