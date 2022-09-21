package g56020.SameGame.JavaFX.view;

import g56020.SameGame.JavaFX.controller.Controller;
import g56020.SameGame.model.Block;
import g56020.SameGame.model.Model;
import g56020.SameGame.model.State;
import g56020.SameGame.util.observer.Observable;
import g56020.SameGame.util.observer.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 * HBox containing the Board
 */
public class BoardHBox extends HBox implements Observer {

    private final Model game;
    private final Controller controller;
    private TilePane board;

    /**
     * Constructor for the HBox which contains the board with the blocks
     *
     * @param game       current instance of the game
     * @param controller current instance of the controller
     * @param size       amount of blocks on each row / column
     */
    public BoardHBox(Model game, Controller controller, int size) {
        this.game = game;
        this.controller = controller;
        game.addObserver(this);

        this.setFillHeight(false);
        createBoard(size);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * creates the board according to a given amount of blocks for each row / column
     *
     * @param size blocks on each row / column
     */
    private void createBoard(int size) {
        board = new TilePane();
        board.setPrefColumns(size);
        showBlocks(board);
        this.getChildren().add(board);
    }

    /**
     * retrieves the blocks array from the model,
     * and replaces the currently shown array with an updated one
     *
     * @param board
     */
    void showBlocks(TilePane board) {
        Block[][] blocks = game.getBlocks();
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks[0].length; col++) {
                char color = blocks[row][col].getColor();
                ColoredBlock coloredButton = new ColoredBlock(controller, color, row, col);
                board.getChildren().add(coloredButton);
            }
        }
    }

    @Override
    public void update(Observable observable, State state, Object args) {
        if (args instanceof String error) {
            if (error.equals("INVALID_BLOCK")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Block!");
                alert.show();
            }
        }
        this.board.getChildren().clear();
        showBlocks(this.board);
    }
}
