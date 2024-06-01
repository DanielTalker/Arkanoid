
package listeners;

import core.Counter;
import game.GameLevel;
import gameObjects.Ball;
import gameObjects.Block;

/**
 * The BallRemover class is responsible for removing balls from the game when
 * they hit a block.
 * It implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a new BallRemover.
     *
     * @param game The game instance
     * @param remainingBalls The counter for tracking the remaining balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever a ball hits a block.
     * It removes the ball from the game and decreases the count of
     * remaining balls by 1.
     *
     * @param beingHit The block that was hit by the ball.
     * @param hitter   The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        // Decrease the count of remaining balls by 1
        remainingBalls.decrease(1);

    }
}
