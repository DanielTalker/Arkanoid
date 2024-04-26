// 322624206 Daniel Talker

package listeners;

import gameObjects.Ball;
import gameObjects.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Classes implementing this interface can perform specific actions when an
 * object is hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit by a ball.
     * The hitter parameter represents the ball that hit the object.
     *
     * @param beingHit The block that was hit
     * @param hitter   The ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}