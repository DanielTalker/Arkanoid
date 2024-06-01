
package animation;

import biuoop.DrawSurface;
import core.Counter;

/**
 * The EndScreen class represents the animation displayed at the end of the game.
 * It shows a message indicating whether the player won or lost, along with
 * their final score.
 */
public class EndScreen implements Animation {
    private boolean isWin;
    private Counter score;


    /**
     * Creates a new EndScreen instance with the specified parameters.
     *
     * @param isWin the flag indicating whether the player won or lost
     * @param score the final score of the player
     */
    public EndScreen(boolean isWin, Counter score) {
        this.isWin = isWin;
        this.score = score;
    }
    /**
     * Performs one frame of the end screen animation.
     * Draws a message indicating whether the player won or lost and their
     * final score on the specified DrawSurface.
     *
     * @param d the DrawSurface to draw the animation frame on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.isWin) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is "
                    + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is "
                    + this.score.getValue(), 32);
        }
    }

    /**
     * Determines whether the end screen animation should stop.
     *
     * @return always returns false since the end screen animation does
     * not stop automatically
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
