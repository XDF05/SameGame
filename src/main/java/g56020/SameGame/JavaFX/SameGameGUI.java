package g56020.SameGame.JavaFX;

import g56020.SameGame.JavaFX.controller.Controller;
import g56020.SameGame.model.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class SameGameGUI extends Application {
    @Override
    public void start(Stage stage) {
        var game = new Game();
        var controller = new Controller(game);
        controller.play(stage);
    }
}
