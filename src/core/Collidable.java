// 322624206 Daniel Talker

package core;

import gameObjects.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface Collidable.
 * The Collidable interface represents objects that can collide with
 * other objects in the game.
 * Objects implementing this interface must provide a way to retrieve their
 * collision shape, and a method to handle collisions with other objects
 * and calculate the resulting velocity change.
 */
public interface Collidable {

    /**
     * Gets the collision shape of this object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Handles a collision with this object at the given collision point,
     * and calculates the new velocity of the colliding object based on the
     * force this object inflicted on it.
     *
     * @param hitter          the ball that hits the collision object
     * @param collisionPoint the point at which the collision occurred.
     * @param currentVelocity the velocity of the colliding object before the collision.
     * @return the new velocity of the colliding object after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}