package g56020.SameGame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    public void initEach() {
        game = new Game();
    }

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
    private Block[][] createBlocks() {
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
     * Verifies whether the game is in the correct state before the start
     */
    @Test
    void start_state_not_started() {
        assertEquals(State.NOT_STARTED, game.getState());
    }

    /**
     * Verifies whether the game is in the correct state after the start
     */
    @Test
    void start_state_started() {
        game.start(3, 5);
        assertEquals(State.WAIT_INPUT, game.getState());
    }

    /**
     * Verifies whether the board containing the blocks is created at the start of the game.
     */
    @Test
    void start_board_created() {
        game.start(3, 5);
        assertNotNull(game.getBlocks());
    }

    /**
     * Verifies whether the state is successfully put to GAME_OVER after giving up
     */
    @Test
    void giveUp() {
        game.start(3, 5);
        game.giveUp();
        assertEquals(game.getState(), State.GAME_OVER);
    }

    /**
     * Verifies if the copyBlocks array is the same as the blocks array after removing the first row
     */
    @Test
    void getCopyBlocks() {
        Block[][] blocks = createBlocks();
        Board board = new Board(blocks);

        game.start(board);
        game.removeBlocks(0, 0);
        Block[][] copyBoard = game.getCopyBlocks();
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                if (blocks[row][col].getColor() != copyBoard[row][col].getColor()) {
                    fail("Copied array is different!");
                }
            }
        }
    }
}