
package gameObjects;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a rectangular block that can be collided with
 * and drawn on a surface.
 * It implements the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    private static final double EPSILON = 0.00001;

    /**
     * Constructs a new Block object with the specified rectangle and color.
     *
     * @param rect  the rectangular shape of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new java.util.ArrayList<>();
    }

    /**
     * Instantiates a new Block with the specified rectangle.
     *
     * @param rect the rectangular shape of the block
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new java.util.ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param rect         the rectangle object of the block
     * @param color        the color of the block
     * @param hitListeners all the hit listeners
     */
    public Block(Rectangle rect, java.awt.Color color,
                 List<HitListener> hitListeners) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = hitListeners;
    }

    /**
     * Constructor.
     *
     * @param rect         the rectangle object of the block
     * @param hitListeners all the hit listeners
     */
    public Block(Rectangle rect, List<HitListener> hitListeners) {
        this.rect = rect;
        this.hitListeners = hitListeners;
    }

    /**
     * Get method.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        if (hitListeners == null) {
            return new java.util.ArrayList<>();
        }
        return hitListeners;
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * this method calculate and return the new velocity after a collision
     * with the block occurred.
     *
     * @param collisionPoint - the point of collision with the block
     * @param currentVelocity - the current velocity of the colliding object
     * @return the new velocity of the colliding object after the collision
     *
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeft = this.rect.getUpperLeft();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        // If the ball hits the top or bottom of the block
        if (((threshold(y, upperLeft.getY()) && dy > 0)
                || (threshold(y, this.rect.getMaxY()) && dy < 0))
                && (upperLeft.getX() <= x + EPSILON
                && this.rect.getMaxX() >= x - EPSILON)) {
            dy = -dy;
        }

        // If the ball hits the left or right of the block
        if (((threshold(x, upperLeft.getX()) && dx > 0)
                || (threshold(x, this.rect.getMaxX()) && dx < 0))
                && (upperLeft.getY() <= y + EPSILON
                && this.rect.getMaxY() >= y - EPSILON)) {
            dx = -dx;
        }

        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        this.rect.fillRectangle(surface);
        //Paint the block frame black
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    /**
     * This method is called once every frame.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * Threshold boolean.
     * Determines whether the absolute difference between two given double
     * values is less than a small threshold value.
     *
     * @param val1 the first double value to compare
     * @param val2 the second double value to compare
     * @return true if the absolute difference between val1 and val2 is less
     * than a small threshold value; false otherwise
     */
    public boolean threshold(double val1, double val2) {
        // if the second double bigger
        if (val2 > val1) {
            return Math.abs(val2 - val1) < EPSILON;
        }
        // if the first double bigger
        return Math.abs(val1 - val2) < EPSILON;
    }

    /**
     * Adds the block to the given game by adding it as a sprite and
     * a collidable object.
     *
     * @param game the game to add the block to
     */
    public void addToGame(GameLevel game) {
        // adds the block as a sprite to the game
        game.addSprite(this);
        // adds the block as a collidable object to the game
        game.addCollidable(this);
    }

    /**
     * Removes this object from the game.
     * Removes the object as a collidable and a sprite from the game.
     *
     * @param game The game to remove this object from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Adds a hit listener to this object.
     *
     * @param hl The hit listener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new java.util.ArrayList<>();
        }
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from this object.
     *
     * @param hl The hit listener to be removed.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new java.util.ArrayList<>();
        } else {
            this.hitListeners.remove(hl);
        }
    }

    /**
     * Notifies all hit listeners about a hit event with the specified ball.
     *
     * @param hitter The ball that hit this object.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners != null) {
            // Make a copy of the hitListeners before iterating over them
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }


    /**
     * Sets the hit listeners for this object.
     *
     * @param hitListeners The hit listeners to be set.
     */
    public void setHitListeners(List<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }
}

