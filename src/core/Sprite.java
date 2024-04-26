// 322624206 Daniel Talker

package core;
import biuoop.DrawSurface;

/**
 * The interface Sprite represents a game object that can be drawn on the
 * screen and is affected by time passing in the game.
 */
public interface Sprite {
    /**
     * Draw on.
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * Notifies the sprite that a unit of time has passed.
     * This allows the sprite to perform any necessary changes in its
     * state or behavior.
     */
    void timePassed();
}