// 322624206 Daniel Talker

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;
import java.util.List;

/**
 * The LevelInformation interface represents the information for a game level.
 * It provides methods to retrieve various properties of the level, such as the
 * number of balls, initial ball velocities,
 * paddle speed, paddle width, level name, background, blocks, and the number
 * of blocks to remove.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of the initial velocities of each ball in the level.
     * Note that the size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */

    int paddleWidth();

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns a list of blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed to clear the level.
     * This number should be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();

}
