// 322624206 Daniel Talker

package animation;

import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that can be displayed on
 * a DrawSurface.
 * It provides methods to update the animation state and determine when the
 * animation should stop.
 */
public interface Animation {

    /**
     * Performs one frame of the animation, updating its state and drawing on
     * the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the animation frame
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
