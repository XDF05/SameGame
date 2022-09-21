package g56020.SameGame.Console.view;

import g56020.SameGame.model.Block;
import g56020.SameGame.model.Model;
import g56020.SameGame.model.State;
import g56020.SameGame.util.observer.Observable;

import java.util.Scanner;

public class MyView implements View {
    private final Model model;

    public MyView(Model game) {
        this.model = game;
        model.addObserver(this);
    }

    @Override
    public void printBlocks() {
        Block[][] blocks = this.model.getBlocks();
        System.out.print("  ");
        for (int col = 0; col < blocks[0].length; col++) {
            System.out.printf("%02d ", col);
        }
        System.out.println();

        for (int row = 0; row < blocks.length; row++) {
            System.out.printf("%02d ", row);
            for (int col = 0; col < blocks[0].length; col++) {
                System.out.print(blocks[row][col].getColor() + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String[] askCommand() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a command: ");
        String input;
        String[] inputs;

        do {
            input = keyboard.nextLine();
            input = input.toLowerCase();
            inputs = input.split(" ");

            if (invalidParam(inputs)) {
                if (!inputs[0].equals("remove")) {
                    System.err.println("Invalid command!");
                }
                if (inputs[0].equals("remove")) {
                    System.err.println("invalid X and Y values");
                }
            }
        } while (invalidParam(inputs));
        return inputs;
    }

    /**
     * Prints the actual score
     */
    private void printScore() {
        System.out.println("Score: " + this.model.getScore());
    }

    /**
     * Prints the latest score
     */
    private void printLastScore() {
        System.out.println("Score from last action: " + this.model.getLastScore());
    }

    /**
     * Prints the remaining blocks
     */
    private void printRemainingBlocks() {
        System.out.println("Amount of remaining blocks: " + this.model.getRemainingBlocks());
    }

    /**
     * Prints whether the user won the game or not
     */
    private void printIsWinner(boolean isWinner) {
        System.out.println();
        System.out.print(isWinner ? "You won!" : "You lost!");
    }


    /**
     * verifies whether the user has entered a valid command
     *
     * @param inputs split command
     * @return whether the command is valid
     */
    private boolean invalidParam(String[] inputs) {
        if (inputs[0].equals("remove")) {
            try {
                Integer.parseInt(inputs[1]);
                Integer.parseInt(inputs[2]);
            } catch (Exception e) {
                System.err.println("Invalid X and Y values");
                return true;
            }
            int row = Integer.parseInt(inputs[1]);
            int col = Integer.parseInt(inputs[2]);
            return row >= model.getBlocks().length || col >= model.getBlocks().length
                    || row < 0 || col < 0;
        }
        return !inputs[0].equals("undo") && !inputs[0].equals("redo") && !inputs[0].equals("stop");
    }

    @Override
    public void update(Observable observable, State state, Object args) {
        printBlocks();
        printScore();
        printLastScore();
        printRemainingBlocks();

        if (state == State.GAME_OVER && args instanceof Boolean isWinner) {
            printIsWinner(isWinner);
        }
        System.out.println();
    }
}
