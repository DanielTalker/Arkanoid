
package animation;

import biuoop.DrawSurface;

/**
 * The PauseScreen class represents the animation displayed when the game is paused.
 * It shows a message instructing the player to press the space key to continue.
 */
public class PauseScreen implements Animation {

    /**
     * Performs one frame of the pause screen animation.
     * Displays a message on the specified DrawSurface indicating the game is paused.
     *
     * @param d the DrawSurface to draw the animation frame on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Determines whether the pause screen animation should stop.
     *
     * @return always returns false since the pause screen animation does not
     * stop automatically
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
