// 322624206 Daniel Talker

package listeners;

import game.GameLevel;
import gameObjects.Ball;
import gameObjects.Block;
import core.Counter;

/**
 * A BlockRemover is in charge of removing blocks from the game, as well
 * as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          The game instance
     * @param removedBlocks The counter for tracking the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * This method is called whenever a block is hit by a ball.
     * It removes the block from the game, removes this listener from the block,
     * and decreases the count of remaining blocks by 1.
     *
     * @param beingHit The block that was hit by the ball
     * @param hitter   The ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        // Remove this listener from the block
        beingHit.removeHitListener(this);
        // Decrease the count of remaining blocks by 1
        remainingBlocks.decrease(1);
    }
}
