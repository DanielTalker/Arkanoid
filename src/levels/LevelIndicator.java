
package levels;

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The LevelIndicator class is responsible for displaying the current level
 * name on the screen.
 * It implements the Sprite interface, allowing it to be drawn on a
 * DrawSurface and added to a GameLevel.
 */
public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * Constructs a new LevelIndicator with the given level name.
     *
     * @param levelName the name of the level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Draws the level indicator on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        // Draw the level name at coordinates (600, 15) with font size 15
        d.drawText(600, 15, "Level Name: " + this.levelName, 15);

    }

    /**
     * Updates the level indicator.
     * Since the level indicator does not change over time,
     * this method does nothing.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Adds the level indicator to the given game.
     *
     * @param g The game to add the level indicator to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
