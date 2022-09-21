package g56020.SameGame.JavaFX.view;

import g56020.SameGame.model.Model;
import g56020.SameGame.model.State;
import g56020.SameGame.util.observer.Observable;
import g56020.SameGame.util.observer.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * HBox which contains the scores
 */
public class ScoreHBox extends HBox implements Observer {
    private final int FONT_SIZE;
    private int score;
    private int lastMoveScore;
    private int remainingBlocks;

    private final Label lScore;
    private final Label lLastMoveScore;
    private final Label lRemainingBlocks;

    /**
     * Constructor for the ScoreHBox which contains:
     * <p>
     * The Score
     * The previous score
     * The amount of blocks remaining
     *
     * @param model     current instance of the model
     * @param FONT_SIZE scores fontsize
     */
    public ScoreHBox(Model model, final int FONT_SIZE) {
        this.FONT_SIZE = FONT_SIZE;
        model.addObserver(this);

        this.lScore = new Label("Score: " + this.score);
        this.lScore.setFont(new Font(FONT_SIZE));

        this.lLastMoveScore = new Label("Last move score: " + this.lastMoveScore);
        this.lLastMoveScore.setFont(new Font(FONT_SIZE));

        this.remainingBlocks = model.getRemainingBlocks();
        this.lRemainingBlocks = new Label("Remaining blocks: " + this.remainingBlocks);
        this.lRemainingBlocks.setFont(new Font(FONT_SIZE));

        this.setAlignment(Pos.CENTER);
        this.setSpacing(150);
        this.setStyle("-fx-border-color: black");

        this.getChildren().addAll(lScore, lLastMoveScore, lRemainingBlocks);
    }

    @Override
    public void update(Observable observable, State state, Object args) {
        if (args.getClass().isArray()) {
            try {
                int[] scores = (int[]) args;

                this.score = scores[0];
                this.lastMoveScore = scores[1];
                this.remainingBlocks = scores[2];

                this.lScore.setText("Score: " + this.score);
                this.lScore.setFont(new Font(FONT_SIZE));

                this.lLastMoveScore.setText("Last move score: " + this.lastMoveScore);
                this.lLastMoveScore.setFont(new Font(FONT_SIZE));

                this.lRemainingBlocks.setText("Remaining blocks: " + this.remainingBlocks);
                this.lRemainingBlocks.setFont(new Font(FONT_SIZE));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
