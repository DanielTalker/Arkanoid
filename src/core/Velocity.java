// 322624206 Daniel Talker

package core;
import geometry.Point;

/**
 * The type Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor for creating a new velocity from a given change in position
     * on the x-axis and y-axis.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the change in position on the x-axis.
     *
     * @return the change in position on the x-axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets the change in position on the y-axis.
     *
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx the new value for the change in position on the x-axis.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy the new value for the change in position on the y-axis.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies the velocity to a given point, by adding the change in position
     * on the x-axis and y-axis to coordinates of a given point,
     * and returns a new point.
     *
     * @param p the point to apply the velocity to.
     * @return a new point after applying the velocity.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }

    /**
     * Creates a new instance of Velocity from a given angle and speed.
     * The angle is measured in degrees, where 0 degrees is pointing to
     * the right.
     * The returned Velocity has the corresponding dx and dy components for
     * the given angle and speed.
     *
     * @param angle the angle of the velocity vector in degrees.
     * @param speed the magnitude of the velocity vector.
     * @return a new instance of Velocity with the corresponding dx and dy components.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Converted the angle from degrees to radians
        double radians = Math.toRadians(angle);
        // Calculate the dx component of the velocity using trigonometric functions
        double dx = speed * Math.sin(radians);
        /* Calculate the dy component of the velocity using trigonometric
        functions, with a negative sign because of the flipped y-axis
        in the coordinate system */
        double dy = -speed * Math.cos(radians);

        // returns a new instance of the Velocity class with the dx and dy
        // components that were calculated
        return new Velocity(dx, dy);
    }

    /**
     * Calculates the magnitude (speed) of the velocity vector.
     *
     * @return the magnitude (speed) of the velocity vector
     */
    public double getSpeed() {
        /* Calculate the magnitude (speed) of the velocity vector using the
        Pythagorean theorem */
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }

}
