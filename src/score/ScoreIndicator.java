
package score;
import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The ScoreIndicator class is responsible for displaying the current
 * score on the screen.
 * It implements the Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructs a new ScoreIndicator.
     *
     * @param score     The Counter object representing the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        int upperLeftX = 0;
        int upperLeftY = 0;
        int width = 800;
        int height = 20;

        d.drawRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(Color.WHITE);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
        d.setColor(Color.black);
        d.drawText(360, 15, "Score: " + score.getValue(), 15);

    }

    /**
     * Updates the score indicator.
     * Since the score indicator does not change over time,
     * this method does nothing.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds the score indicator to the given game.
     *
     * @param g The game to add the score indicator to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
