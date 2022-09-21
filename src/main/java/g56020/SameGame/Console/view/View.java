package g56020.SameGame.Console.view;

import g56020.SameGame.util.observer.Observer;

public interface View extends Observer {

    /**
     * Prints the every block inside the board
     */
    void printBlocks();

    /**
     * asks the user for a command
     * Possibilities: 'remove x, y', 'undo', 'redo', 'stop'
     */
    String[] askCommand();
}
