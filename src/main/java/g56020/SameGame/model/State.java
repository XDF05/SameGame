package g56020.SameGame.model;

/**
 * Different states of the game.
 */
public enum State {
    /**
     * The game hasn't started yet.
     *
     * Only possibility is to start the game.
     */
    NOT_STARTED,
    /**
     * Waiting for user input.
     *
     * Possibilities: Remove Block, Undo, Redo, Stop
     */
    WAIT_INPUT,
    /**
     * The game is over.
     *
     * No more removable blocks left.
     *
     * Or the game has been manually stopped.
     */
    GAME_OVER
}
