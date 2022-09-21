package g56020.SameGame.JavaFX.view;

import g56020.SameGame.JavaFX.controller.Controller;
import javafx.scene.control.Button;

/**
 * Representation of a colored Block
 */
public class ColoredBlock extends Button {

    private final int row;
    private final int col;

    /**
     * Creates a Block which a given color and position
     *
     * @param controller current instance of the controller
     * @param color      given color
     * @param row        given row
     * @param col        given column
     */
    public ColoredBlock(Controller controller, char color, int row, int col) {
        this.row = row;
        this.col = col;

        String btnStyle =
                "-fx-background-radius: 0;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2px;";
        switch (color) {
            case 'r' -> btnStyle += "-fx-background-color: #FF0000;"; //red
            case 'g' -> btnStyle += "-fx-background-color: #00FF00;"; //green
            case 'b' -> btnStyle += "-fx-background-color: #0000FF;"; //blue
            case 'y' -> btnStyle += "-fx-background-color: #FFFF00;"; //yellow
            case 'p' -> btnStyle += "-fx-background-color: #800080;"; //purple
        }
        /*
         * sets the background color of the block to its color
         * and size to 30x30
         */
        this.setStyle(btnStyle);
        this.setPrefWidth(30);
        this.setPrefHeight(30);

        //removes the block (and its adjacent blocks) when clicked
        this.setOnAction(e -> controller.execute(this.row, this.col));
    }
}
