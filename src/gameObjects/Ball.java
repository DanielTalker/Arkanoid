
package gameObjects;

import core.CollisionInfo;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * This ball class defines a Ball object, with properties such as its center
 * point, radius, color, velocity, and start/end points of  the ball's frame.
 * It has methods to get and set these properties, and to move the ball
 * according to its velocity. The class also includes a method to draw
 * the ball on a surface.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;


    /**
     * Instantiates a new Ball with the specified center, radius, and color.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new Ball object with the specified x and y coordinates,
     * radius, and color. It calls to constructor that create a new instance
     * of the Ball class with a new Point object
     *
     * @param x               the x coordinate of the center point of the ball
     * @param y               the y coordinate of the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment of the game
     */
    public Ball(int x, int y, int r, java.awt.Color color,
                GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color, gameEnvironment);
    }

    /**
     * Sets center.
     *
     * @param p the point to change the ball center to
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * Draw on.
     * Draws the ball on a given DrawSurface.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // set the color of the ball according to its color
        d.setColor(this.color);
        // draw this ball
        d.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.radius);
        d.setColor(Color.BLACK);
        d.drawCircle((int) center.getX(),
                (int) center.getY(), radius);
    }

    /**
     * Time passed.
     * For the ball, it should move one step
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets x.
     *
     * @return the x-value of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y-value of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the radius (int) of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the velocity of the ball to specific speed.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball from a given dx and dy.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     * Moves the ball by one step according to its velocity,
     * and ensures it stays within the boundaries of the given
     * width and height.
     */
    public void moveOneStep() {
        double currentDx = this.getVelocity().getDx();
        double currentDy = this.getVelocity().getDy();
        // If velocity is zero do not move
        if (currentDx == 0 && currentDy == 0) {
            return;
        }

        // Calculate the next center of the ball
        double nextXValue = this.center.getX() + this.getVelocity().getDx();
        double nextYValue = this.center.getY() + this.getVelocity().getDy();

        // Calculate the trajectory line for check for collision point
        Point startTrajectory = this.center;
        Point endTrajectory = new Point(nextXValue, nextYValue);
        Line trajectory = new Line(startTrajectory, endTrajectory);
        CollisionInfo objectInfo = this.gameEnvironment.getClosestCollision(trajectory);

        // If there is no collision point continue as usual
        if (objectInfo == null) {
            this.center = new Point(nextXValue, nextYValue);
            return;
        }

        // If there is collision point set the velocity according to it
        if (objectInfo != null) {
            Point closest = objectInfo.collisionPoint();
            this.setVelocity(objectInfo.collisionObject().hit(this, closest,
                    this.velocity));
        }
    }

    /**
     * Adds this object to the game as a sprite.
     *
     * @param game The game to add this object to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Removes this object from the game as a sprite.
     *
     * @param game The game to remove this object from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}