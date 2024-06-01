
package game;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.Animation;
import biuoop.Sleeper;
import core.Collidable;
import core.Counter;
import core.Sprite;
import core.Velocity;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Paddle;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.List;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.LevelIndicator;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import score.ScoreTrackingListener;
import score.ScoreIndicator;

/**
 * The Game class defines a game consisting of Blocks, a Ball, and a Paddle.
 * The game is initialized by creating Blocks of various colors and patterns,
 * creating the borders of the game screen, and creating the Balls and Paddle.
 * The game can also add Collidable objects and Sprite objects to the
 * environment.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle gamePaddle;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private static final int BALL_SIZE = 5;
    private static final int DEATH_BLOCK = 3;

    /**
     * Instantiates a new Game.
     * Creates a new Game object.
     *
     * @param sprites     the collection of all sprites in the game
     * @param environment the game environment
     * @param gui         the GUI used to display the game
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment, GUI gui) {
        this.sprites = sprites;
        this.environment = environment;
        this.gui = gui;
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.runner = new AnimationRunner(this.gui, 60, new Sleeper());
        this.running = true;
        this.keyboard = this.runner.getGui().getKeyboardSensor();
    }

    /**
     * Instantiates a new Game level.
     *
     * @param sprites          the sprites
     * @param environment      the environment
     * @param gui              the gui
     * @param levelInformation the level information
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment,
                     GUI gui, LevelInformation levelInformation) {
        this.sprites = sprites;
        this.environment = environment;
        this.gui = gui;
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.runner = new AnimationRunner(this.gui, 60, new Sleeper());
        this.running = true;
        this.keyboard = this.runner.getGui().getKeyboardSensor();
        this.levelInformation = levelInformation;
    }

    /**
     * Instantiates a new Game level.
     *
     * @param sprites          the sprites
     * @param environment      the environment
     * @param gui              the gui
     * @param levelInformation the level information
     * @param runner           the runner
     * @param score            the score
     */
    public GameLevel(SpriteCollection sprites, GameEnvironment environment,
                     GUI gui, LevelInformation levelInformation,
                     AnimationRunner runner, Counter score) {
        this.sprites = sprites;
        this.environment = environment;
        this.gui = gui;
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.runner = runner;
        this.running = true;
        this.keyboard = runner.getGui().getKeyboardSensor();
        this.levelInformation = levelInformation;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable object from the game environment.
     *
     * @param c the Collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite object from the games' SpriteCollection.
     *
     * @param s the Sprite object to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game by creating the Blocks, Ball
     * and Paddle and adding them to the game.
     */
    public void initialize() {
        // Create the screen block and add it to the game
        Block block1 = (Block) this.levelInformation.getBackground();
        block1.addToGame(this);

        // Create the blocks and add them to the game
        createBlocks();

        // Create the borders and add them to the game
        createBorders();
        // Create the balls and add them to the game
        this.createBalls();

        // Creating level name and scoring status indicators
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        LevelIndicator levelName = new LevelIndicator(this.levelInformation.levelName());
        indicator.addToGame(this);
        levelName.addToGame(this);
    }

    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.remainingBlocks.increase(this.levelInformation.numberOfBlocksToRemove());


        for (Block block : this.levelInformation.blocks()) {
            List<HitListener> blockRemoverList = new java.util.ArrayList<>();
            blockRemoverList.add(blockRemover);
            blockRemoverList.add(scoreTrackingListener);
            block.setHitListeners(blockRemoverList);
            block.addToGame(this);
        }
    }

    /**
     * Creates two balls, sets their color, size, initial position and
     * velocity, and adds them to the game environment.
     * Uses the environment of the current game to add the balls to the game.
     */
    private void createBalls() {
        int paddleWidth = this.levelInformation.paddleWidth();
        int startX = (800 - paddleWidth) / 2 + (paddleWidth / 2);
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        int numBalls = this.levelInformation.numberOfBalls();
        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(new Point(startX, 550),
                    BALL_SIZE, Color.WHITE, this.environment);
            ball.setVelocity(velocities.get(i));
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }

    }

    /**
     * Creates the borders of the game screen.
     * There are four blocks representing the top, bottom, left and right
     * borders of the screen. The blocks are added to the game.
     */
    public void createBorders() {
        BallRemover ballRemover = new BallRemover(this, remainingBalls);

        /* Creating an array of four blocks that will be used in the frame
        of the screen */
        Block[] borders = {
                new Block(new Rectangle(new Point(770, 0), 30, 630, Color.GRAY)),
                new Block(new Rectangle(new Point(0, 0), 30, 630, Color.GRAY)),
                new Block(new Rectangle(new Point(0, 20), 800, 30, Color.GRAY)),
                new Block(new Rectangle(new Point(0, 615), 800, 30, Color.GRAY))
        };

        borders[DEATH_BLOCK].addHitListener(ballRemover);
        // Add the blocks to the game
        for (Block b : borders) {
            b.addToGame(this);
        }
    }

    /**
     * Create a new Paddle object and add it to the game.
     * The Paddle object is created with a keyboard sensor, color,
     * size and location.
     */
    private void createPaddle() {
        // Get the keyboard sensor from the game's GUI
        KeyboardSensor keyboard = this.keyboard;
        // Create a new color for the paddle
        Color mustardYellow = new Color(205, 185, 75);
        // Create a rectangle for the paddle's shape
        int paddleWidth = this.levelInformation.paddleWidth();
        int startX = (800 - paddleWidth) / 2;
        Rectangle rectForPaddle = new Rectangle(new Point(startX, 565),
                paddleWidth, 15, mustardYellow);
        // Create a new Paddle object and add it to the game
        this.gamePaddle =
                new Paddle(rectForPaddle, rectForPaddle.getColor(), keyboard);
        this.gamePaddle.addToGame(this);
    }

    /**
     * Run the game and start the animation loop.
     */
    public void run() {
        // Create the paddle and add it to the game
        this.createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Performs one frame of the game animation.
     * Draws all the sprites on the given surface, notifies all sprites
     * of time passed,
     * and checks if the game should stop based on the remaining blocks and balls.
     *
     * @param d the draw surface on which to draw the game animation
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // If there are no balls or blocks left in the game, the game should close
        if (this.remainingBlocks.getValue() == 0
                || this.remainingBalls.getValue() == 0) {
                /* If there was a victory, meaning all the blocks were removed,
                add 100 points to the score */
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
            }
            this.running = false;
        }

        // If the user enter pause - 'p'
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * Checks if the game animation should stop.
     *
     * @return true if the game animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
