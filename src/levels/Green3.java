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
 * The Green3 class represents the information and configuration for the
 * "Green 3" level.
 * It implements the LevelInformation interface.
 */
public class Green3 implements LevelInformation {

    private static final int BALLS_NUM = 2;
    private static final int PADDLE_SPEED = 7;
    private static final int PADDLE_WIDTH = 80;
    private static final int BLOCKS_NUM = 40;
    private static final int GUI_WIDTH = 1000;
    private static final int GUI_HEIGHT = 1000;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int START_X = 720;
    private static final int START_Y = 135;

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
        velocities.add(new Velocity(3, -4));
        velocities.add(new Velocity(-3, -4));

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
        return "Green 3";
    }

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    @Override
    public Sprite getBackground() {
        Color color = new Color(7, 82, 7);
        Block background = new Block(new Rectangle(new Point(0, 0),
                GUI_WIDTH, GUI_HEIGHT, color));
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
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW,
                Color.BLUE, Color.WHITE};
        ArrayList<Block> blocks = new ArrayList<>();
        int blocksNum = 10;
        int layerNum = 0;
        // create blocks in a pattern
        for (int i = 0; i < 5; i++) {
            Block[] blocks1 = createBlocks(blocksNum, colors[i], layerNum);
            for (Block b : blocks1) {
                blocks.add(b);
            }
            blocksNum--;
            layerNum++;
        }
        return blocks;
    }

    /**
     * Creates an array of Block objects with the specified parameters.
     *
     * @param numBlocks the number of blocks to create
     * @param color the color of the blocks
     * @param layer the layer of the blocks
     * @return an array of Block objects with the specified parameters
     */
    private Block[] createBlocks(int numBlocks, Color color, int layer) {
        Block[] blocks = new Block[numBlocks];
        // starting x and y coordinates for the blocks
        int startX = START_X;
        int startY = START_Y + BLOCK_HEIGHT * layer;

        // loop through each block and create a new Block object
        for (int i = 0; i < numBlocks; i++) {
            // calculate the upper left point of the block's Rectangle object
            Point upperLeft = new Point(startX - BLOCK_WIDTH * i, startY);
            // create a Rectangle object with the upper left point, width,
            // height, and color
            Rectangle rectangle = new Rectangle(upperLeft,
                    BLOCK_WIDTH, BLOCK_HEIGHT, color);
            // create a new Block object and add it to the blocks array
            blocks[i] = new Block(rectangle);
        }
        // return the array of Blocks
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
