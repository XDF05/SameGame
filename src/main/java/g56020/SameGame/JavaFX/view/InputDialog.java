package g56020.SameGame.JavaFX.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Dialog box which asks for a size [5-20] and difficulty [3-5]
 */
public class InputDialog {
    private int size = MIN_SIZE, difficulty = MIN_DIFF;

    private static final int MIN_SIZE = 5;
    private static final int MAX_SIZE = 20;

    private static final int MIN_DIFF = 3;
    private static final int MAX_DIFF = 5;

    /**
     * Creates and displays an input dialog
     * Asks the user for a size [5-20]
     * Asks the user for a difficulty [3-5]
     */
    public void display() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Spinner<Integer> size = new Spinner<>();
        SpinnerValueFactory<Integer> sizeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_SIZE, MAX_SIZE, MIN_SIZE);

        Spinner<Integer> difficulty = new Spinner<>();
        SpinnerValueFactory<Integer> difficultyValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_DIFF, MAX_DIFF, MIN_DIFF);

        size.setValueFactory(sizeValueFactory);
        difficulty.setValueFactory(difficultyValueFactory);

        Button button = new Button("Submit");
        button.setOnAction(e -> {
            this.size = size.getValue();
            this.difficulty = difficulty.getValue();
            stage.close();
        });

        Label sizeLabel = new Label("Size");
        Label difficultyLabel = new Label("Difficulty:");
        difficultyLabel.setMinWidth(Region.USE_PREF_SIZE);

        GridPane layout = new GridPane();

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        layout.add(size, 1, 0);
        layout.add(difficulty, 1, 1);
        layout.add(button, 1, 2);
        layout.add(sizeLabel, 0, 0);
        layout.add(difficultyLabel, 0, 1);

        Scene scene = new Scene(layout, 300, 120);
        stage.setTitle("Start Menu");
        stage.setScene(scene);
        stage.showAndWait();

    }

    /**
     * getter for the values entered by the user
     *
     * @return array containing the entered size and difficulty
     */
    public int[] getValues() {
        return new int[]{this.size, this.difficulty};
    }

}
