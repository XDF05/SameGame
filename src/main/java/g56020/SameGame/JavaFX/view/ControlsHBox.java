package g56020.SameGame.JavaFX.view;

import g56020.SameGame.JavaFX.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * HBox containing the: undo, redo and stop buttons
 */
public class ControlsHBox extends HBox {

    /**
     * Constructor for the HBox which contains the: undo, redo and stop buttons
     *
     * @param controller current instance of the controller
     * @param FONT_SIZE  button's font size
     */
    public ControlsHBox(Controller controller, final int FONT_SIZE) {

        Button undo = new Button("Undo");
        undo.setFont(new Font(FONT_SIZE));

        Button redo = new Button("Redo");
        redo.setFont(new Font(FONT_SIZE));

        Button stop = new Button("Stop");
        stop.setFont(new Font(FONT_SIZE));

        undo.setOnAction(e -> controller.undo());

        redo.setOnAction(e -> controller.redo());

        stop.setOnAction(e -> controller.stop());

        this.setAlignment(Pos.CENTER);
        this.setSpacing(150);
        this.setPadding(new Insets(10, 0, 10, 0));
        this.setStyle("-fx-border-color: black");

        this.getChildren().addAll(undo, redo, stop);
    }
}
