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
 * The WideEasy class represents the information and configuration for the
 * "Wide Easy" level.
 * It implements the LevelInformation interface.
 */
public class WideEasy implements LevelInformation {

    private static final int BALLS_NUM = 10;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 550;
    private static final int BLOCKS_NUM = 15;
    private static final int GUI_WIDTH = 1000;
    private static final int GUI_HEIGHT = 1000;

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
     * Note that the size of the returned list should be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < BALLS_NUM; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(315 + (i * 10), 5));
        }
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
        return "Wide Easy";
    }

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        Block background = new Block(new Rectangle(new Point(0, 0),
                GUI_WIDTH, GUI_HEIGHT, Color.WHITE));
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
        Color[] colors = {Color.RED, Color.RED, Color.ORANGE,
                Color.ORANGE, Color.YELLOW, Color.YELLOW, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK,
                Color.PINK, Color.CYAN, Color.CYAN};
        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Block block = new Block(new Rectangle(new Point(30 + (50 * i), 210),
                    50, 30, colors[i]));
            blocks.add(block);

        }
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
