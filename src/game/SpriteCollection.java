
package game;

import core.Sprite;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It allows adding and removing sprites, and calling timePassed()
 * and drawOn(d) on all sprites in the collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        // Create a new ArrayList to store the sprites
        sprites = new ArrayList<>();
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return the list of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Adds the given sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removes the specified sprite from the game.
     *
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * Calls timePassed() on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            if (sprites.get(i) != null) {
                sprites.get(i).timePassed();
            }
        }
    }

    /**
     * Calls drawOn(d) on all sprites in the collection.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }
}
