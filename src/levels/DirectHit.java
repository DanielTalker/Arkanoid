// 322624206 Daniel Talker

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The DirectHit class represents the information and configuration for the
 * "Direct Hit" level.
 * It implements the LevelInformation interface.
 */
public class DirectHit implements LevelInformation {

    private static final int BALLS_NUM = 1;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 80;
    private static final int BLOCKS_NUM = 1;
    private static final int GUI_WIDTH = 1000;
    private static final int GUI_HEIGHT = 1000;
    private static final double BLOCK_X_UPPER_LEFT = 380;
    private static final double BLOCK_Y_UPPER_LEFT = 170;

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return BALLS_NUM;
    }

    /**
     * Returns a list of the initial velocities of each ball in the level.
     * Note that the size of the returned list should be equal to the number
     * of balls.
     *
     * @return a list of initial ball velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        Velocity velocity = new Velocity(0, -3);
        velocities.add(velocity);

        return velocities;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Returns the name of the level.
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        Block background = new Block(new Rectangle(new Point(0, 0),
                GUI_WIDTH, GUI_HEIGHT, Color.black));
        return background;
    }

    /**
     * Returns a list of blocks that make up the level.
     * Each block contains its size, color, and location.
     *
     * @return a list of blocks
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(BLOCK_X_UPPER_LEFT, BLOCK_Y_UPPER_LEFT),
                30, 30, Color.RED));
        blocks.add(block);

        return blocks;
    }

    /**
     * Returns the number of blocks that should be removed to clear the level.
     * This number should be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS_NUM;
    }
}
