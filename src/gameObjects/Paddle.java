// 322624206 Daniel Talker

package gameObjects;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleShape;
    private Color paddleColor;
    private static final int NUM_REGIONS = 5;
    private static final int PADDLE_SPEED = 7;
    private static final double EPSILON = 0.00001;

    /**
     * Instantiates a new Paddle.
     *
     * @param paddleShape the paddle shape
     * @param paddleColor the paddle color
     * @param keyboard    the keyboard
     */
    public Paddle(Rectangle paddleShape, Color paddleColor,
                  KeyboardSensor keyboard) {
        this.paddleShape = paddleShape;
        this.paddleColor = paddleColor;
        this.keyboard = keyboard;
    }

    /**
     * Gets paddle shape.
     *
     * @return the paddle shape
     */
    public Rectangle getPaddleShape() {
        return this.paddleShape;
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(paddleColor);
        d.fillRectangle((int) paddleShape.getUpperLeft().getX(),
                (int) paddleShape.getUpperLeft().getY(),
                (int) paddleShape.getWidth(), (int) paddleShape.getHeight());
        // Paint the paddle frame black
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) paddleShape.getUpperLeft().getX(),
                (int) paddleShape.getUpperLeft().getY(),
                (int) paddleShape.getWidth(), (int) paddleShape.getHeight());
    }

    /**
     * Responds to the passage of time by checking for left or right arrow key
     * presses on the keyboard.
     * If the left arrow key is pressed, moves the object to the left.
     * If the right arrow key is pressed, moves the object to the right.
     */
    @Override
    public void timePassed() {
        // Check if the left arrow key is currently being pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // Move the object to the left
            moveLeft();

        // Otherwise, check if the right arrow key is currently being pressed
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // Move the object to the right
            moveRight();
        }
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleShape;
    }

    /**
     * Changes the ball's velocity according to the location it hit on the
     * paddle.
     *
     * @param collisionPoint the point where the ball hits the paddle
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after the ball hits the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //get the upper left point of the paddle
        Point upperLeft = paddleShape.getUpperLeft();
        //get the current dx and dy of the ball's velocity
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double regionLength = this.paddleShape.getWidth() / NUM_REGIONS;
        double paddleWidth = this.paddleShape.getWidth();
        double paddleHeight = this.paddleShape.getHeight();
        double ballSpeed = currentVelocity.getSpeed();
        // set the radius of the ball
        double ballRadius = 5;

        // check if the ball is stuck inside the paddle
        if (x > upperLeft.getX() && x < upperLeft.getX() + paddleWidth
                && y > upperLeft.getY() && y < upperLeft.getY() + paddleHeight) {
            // move the ball outside from the paddle's area
            if (dx > 0) {
                x = upperLeft.getX() + paddleWidth + ballRadius;
            } else {
                x = upperLeft.getX() - ballRadius;
            }
            // update the collision point
            collisionPoint = new Point(x, y);
        }
        x = collisionPoint.getX();
        y = collisionPoint.getY();

        // Divide the paddle into regions and change the ball's velocity
        // based on which region it hits
        if (threshold(y, upperLeft.getY())) {
            // If the ball collided in the first part
            if (x >= upperLeft.getX() - EPSILON
                    && x < upperLeft.getX() + regionLength + EPSILON) {
                return Velocity.fromAngleAndSpeed(300, ballSpeed);

            // If the ball collided in the second part
            } else if (x >= upperLeft.getX() - EPSILON + regionLength
                    && x < upperLeft.getX() + 2 * regionLength + EPSILON) {
                return Velocity.fromAngleAndSpeed(330, ballSpeed);

            // If the ball collided in the third part
            } else if (x >= upperLeft.getX() + 2 * regionLength - EPSILON
                    && x < upperLeft.getX() + 3 * regionLength + EPSILON) {
                return new Velocity(dx, -dy);

            // If the ball collided in the fourth part
            } else if (x >= upperLeft.getX() + 3 * regionLength - EPSILON
                    && x < upperLeft.getX() + 4 * regionLength + EPSILON) {
                return Velocity.fromAngleAndSpeed(30, ballSpeed);

            // If the ball collided in the fifth part
            } else if (x >= upperLeft.getX() + 4 * regionLength - EPSILON
                    && x <= upperLeft.getX() + paddleWidth + EPSILON) {
                return Velocity.fromAngleAndSpeed(60, ballSpeed);
            }
        }

        // If the ball collided the left side of the paddle
        if (threshold(x, upperLeft.getX())) {
            if (y >= upperLeft.getY() - EPSILON
                    && y <= upperLeft.getY() + paddleHeight + EPSILON) {
                return Velocity.fromAngleAndSpeed(300, ballSpeed);
            }
        }
        // If the ball collided the right side of the paddle
        if (threshold(x, upperLeft.getX() + paddleWidth)) {
            if (y >= upperLeft.getY() - EPSILON
                    && y <= upperLeft.getY() + paddleHeight + EPSILON) {
                return Velocity.fromAngleAndSpeed(60, ballSpeed);
            }
        }

        return new Velocity(-dx, dy);
    }

    /**
     * Move left.
     * Moves the paddle to the left while keeping it inside the screen.
     */
    public void moveLeft() {
        // Get the current X coordinate of the paddle's upper-left corner
        double currentX = this.paddleShape.getUpperLeft().getX();
        // Set the minimum X coordinate to keep the paddle inside the screen
        double minX = 30;
        // Calculate the maximum amount by which the paddle can be moved to the left
        double dx = Math.max(-PADDLE_SPEED, -currentX + minX);
        // Calculate the new X and Y coordinates for the paddle's upper-left corner
        double newX = currentX + dx;
        double newY = this.paddleShape.getUpperLeft().getY();
        // Create a new Point object representing the paddle's new upper-left corner
        Point newUpperLeft = new Point(newX, newY);
        // Create a new Rectangle object and assign it to the paddleShape
        this.paddleShape = new Rectangle(newUpperLeft, this.paddleShape.getWidth(),
                this.paddleShape.getHeight(), this.paddleColor);
    }

    /**
     * Move right.
     * Moves the paddle to the right while keeping it inside the screen.
     */
    public void moveRight() {
        // Get the current X coordinate of the paddle's upper-left corner
        double currentX = this.paddleShape.getUpperLeft().getX();
        // Set the maximum X coordinate to keep the paddle inside the screen
        double maxX = 800 - this.paddleShape.getWidth() - 30;
        // Calculate the minimum amount by which the paddle can be moved to the right
        double dx = Math.min(PADDLE_SPEED, maxX - currentX);
        // Calculate the new X and Y coordinates for the paddle's upper-left corner
        double newX = currentX + dx;
        double newY = this.paddleShape.getUpperLeft().getY();
        // Create a new Point object representing the paddle's new upper-left corner
        Point newUpperLeft = new Point(newX, newY);
        // Create a new Rectangle object and assign it to the paddleShape
        this.paddleShape = new Rectangle(newUpperLeft, this.paddleShape.getWidth(),
                this.paddleShape.getHeight(), this.paddleColor);
    }


    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        // adds the paddle as a sprite to the game
        game.addSprite(this);
        // adds the paddle as a collidable object to the game
        game.addCollidable(this);
    }

    /**
     * Threshold boolean.
     * Determines whether the absolute difference between two given double
     * values is less than a small threshold value.
     *
     * @param val1 the first double value to compare
     * @param val2 the second double value to compare
     * @return true if the absolute difference between val1 and val2 is less
     * than a small threshold value; false otherwise
     */
    public boolean threshold(double val1, double val2) {
        // if the second double bigger
        if (val2 > val1) {
            return Math.abs(val2 - val1) < EPSILON;
        }
        // if the first double bigger
        return Math.abs(val1 - val2) < EPSILON;
    }
}