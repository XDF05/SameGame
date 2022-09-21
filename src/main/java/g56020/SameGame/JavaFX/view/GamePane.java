package g56020.SameGame.JavaFX.view;

import g56020.SameGame.JavaFX.controller.Controller;
import g56020.SameGame.model.Model;
import g56020.SameGame.model.State;
import g56020.SameGame.util.observer.Observable;
import g56020.SameGame.util.observer.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Pane which holds the Scoreboard, the game board and the controls
 */
public class GamePane extends BorderPane implements Observer {
    private static final int FONT_SIZE = 20;

    private final Controller controller;
    private final StackPane centerPane;

    /**
     * Constructor the game pane
     *
     * @param game current instance of the game
     * @param ctrl current instance of the controller
     * @param size amount of blocks on each row / column
     */
    public GamePane(Model game, Controller ctrl, int size) {
        this.controller = ctrl;
        game.addObserver(this);
        ScoreHBox scoreHBox = new ScoreHBox(game, FONT_SIZE);
        centerPane = new StackPane();
        BoardHBox boardHBox = new BoardHBox(game, ctrl, size);
        ControlsHBox controlsHBox = new ControlsHBox(ctrl, FONT_SIZE);

        centerPane.getChildren().add(boardHBox);

        this.setTop(scoreHBox);
        this.setCenter(centerPane);
        this.setBottom(controlsHBox);
    }

    /**
     * replaces the Board and Control HBox's, with the end pane.
     * <p>
     * The end pane contains the Scores
     * Whether the game is won
     * a restart / exit button
     *
     * @param ctrl     current instance of the controller
     * @param isWinner whether the user won
     */
    private void endPane(Controller ctrl, boolean isWinner) {
        HBox buttons = new HBox();
        Label gameOverLabel = new Label();
        gameOverLabel.setText(isWinner ? "You won!" : "You lost!");

        Button restartBtn = new Button("Restart");
        Button exitBtn = new Button("exit");
        restartBtn.setOnAction(
                event -> {
                    exit();
                    ctrl.restart();
                }
        );
        exitBtn.setOnAction(event -> exit());

        gameOverLabel.setFont(new Font(FONT_SIZE));
        restartBtn.setFont(new Font(FONT_SIZE));
        exitBtn.setFont(new Font(FONT_SIZE));

        gameOverLabel.setAlignment(Pos.CENTER);
        gameOverLabel.setMaxWidth(Double.MAX_VALUE);
        gameOverLabel.setMaxHeight(30);

        buttons.setSpacing(150);
        buttons.setPadding(new Insets(10, 0, 10, 0));
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle("-fx-border-color: black");

        buttons.getChildren().addAll(restartBtn, exitBtn);
        centerPane.getChildren().add(gameOverLabel);
        gameOverLabel.setStyle(isWinner ? "-fx-background-color: #ADFF2F;" : "-fx-background-color: #FF0000;");

        this.setBottom(buttons);
    }

    /**
     * Closes the application.
     */
    private void exit() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    @Override
    public void update(Observable observable, State state, Object arg) {
        if (state == State.GAME_OVER && arg instanceof Boolean isWinner) {
            isWinner = (boolean) arg;
            endPane(controller, isWinner);
        }
    }
}
