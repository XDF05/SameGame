package g56020.SameGame.Console;

import g56020.SameGame.Console.controller.Controller;
import g56020.SameGame.model.Game;

public class SameGameConsole {
    public static void main(String[] args) {
        var game = new Game();
        var controller = new Controller(game);
        controller.play();
    }
}
