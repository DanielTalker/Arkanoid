package score;

import core.Counter;
import gameObjects.Ball;
import gameObjects.Block;
import listeners.HitListener;

/**
 * The ScoreTrackingListener class is responsible for tracking the score
 * in the game.
 * It implements the HitListener interface.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener.
     *
     * @param scoreCounter The counter for tracking the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * It increases the current score by 5 and removes this listener
     * from the block.
     *
     * @param beingHit The block that was hit by the ball.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Increase the current score by 5
        this.currentScore.increase(5);
        // Remove this listener from the block
        //beingHit.removeHitListener(beingHit.getHitListeners().get(0));
    }
}
