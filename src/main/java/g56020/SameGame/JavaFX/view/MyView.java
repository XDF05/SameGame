package g56020.SameGame.JavaFX.view;

import g56020.SameGame.JavaFX.controller.Controller;
import g56020.SameGame.model.Model;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * View for the graphical version of the SameGame application
 */
public class MyView {
    private final Model model;
    private final Controller controller;

    private int size;
    private int difficulty;

    /**
     * Constructor for the main view
     *
     * @param controller current instance of the controller
     * @param game       current instance of the game
     */
    public MyView(Controller controller, Model game) {
        this.controller = controller;
        this.model = game;
    }

    /**
     * Creates and shows the windows of the game
     *
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("g56020 - SameGame");
        startMenu();
        this.model.start(difficulty, size);

        StackPane root = new StackPane();
        GamePane gamePane = new GamePane(model, controller, size);

        root.getChildren().addAll(gamePane);

        Scene scene = new Scene(root, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * calls the inputDialog and retrieves the size and difficulty
     */
    private void startMenu() {
        InputDialog inputDialog = new InputDialog();
        inputDialog.display();
        int[] inputs = inputDialog.getValues();
        this.size = inputs[0];
        this.difficulty = inputs[1];
    }
}
