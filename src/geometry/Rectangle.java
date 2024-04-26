// 322624206 Daniel Talker

package geometry;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Rectangle class represents a rectangle in a 2D space, defined by an
 * upper-left point, width, height and color.
 * The class provides methods for calculating intersection points between the
 * rectangle's edges and a line, as well as getters for retrieving the
 * rectangle's width, height, upper-left point and color.
 * Additionally, the class provides a method for filling the rectangle on a
 * given DrawSurface with the specified color.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Instantiates a new Rectangle.
     * Create a new rectangle with location, width/height and color.
     *
     * @param upperLeft the upper left Point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Instantiates a new Rectangle.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft the upper left Point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Sets color.
     *
     * @param color the color to change the rectangle color to.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Calculates the intersection points between the specified line
     * and the edges of a rectangle.
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     *
     * @param line the line to check for intersection with the rectangle edges
     * @return a list of intersection points (possibly empty)
     */
    public List<Point> intersectionPoints(Line line) {
        // Initialize points to the vertices of the rectangle
        Point upperLeft = this.getUpperLeft();
        Point upperRight = new Point(upperLeft.getX() + this.getWidth(),
                upperLeft.getY());
        Point bottomRight = new Point(upperRight.getX(),
                upperRight.getY() + this.getHeight());
        Point bottomLeft = new Point(upperLeft.getX(), bottomRight.getY());

        // Create the four lines of the rectangle
        Line leftSide = new Line(upperLeft, bottomLeft);
        Line rightSide = new Line(upperRight, bottomRight);
        Line topSide = new Line(upperLeft, upperRight);
        Line bottomSide = new Line(bottomLeft, bottomRight);

        List<Point> allIntersections = new ArrayList<>();

        // Check if line intersects top edge
        Point intersectionPoint = line.intersectionWith(topSide);
        if (intersectionPoint != null) {
            allIntersections.add(intersectionPoint);
        }

        // Check if line intersects right edge
        intersectionPoint = line.intersectionWith(rightSide);
        if (intersectionPoint != null) {
            allIntersections.add(intersectionPoint);
        }

        // Check if line intersects bottom edge
        intersectionPoint = line.intersectionWith(bottomSide);
        if (intersectionPoint != null) {
            allIntersections.add(intersectionPoint);
        }

        // Check if line intersects left edge
        intersectionPoint = line.intersectionWith(leftSide);
        if (intersectionPoint != null) {
            allIntersections.add(intersectionPoint);
        }

        return allIntersections;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get color.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets max x.
     *
     * @return the maximum x value of the rectangle's vertices
     */
    public double getMaxX() {
        // The maximum x-value in the rectangle is that of the right point
        return this.getUpperLeft().getX() + this.getWidth();
    }

    /**
     * Gets max y.
     *
     * @return the maximum y value of the rectangle's vertices
     */
    public double getMaxY() {
        //The maximum y-value in the rectangle is that of the top point
        return this.getUpperLeft().getY() + this.getHeight();
    }

    /**
     * Fill the rectangle on the given surface with the specified color.
     *
     * @param surface the surface to draw on
     */
    public void fillRectangle(DrawSurface surface) {
        // Set the rectangle color
        if (this.color == null) {
            this.color = Color.green;
        }
        surface.setColor(color);
        // Fill the rectangle on the given surface using the upper-left
        // coordinates, width and height of the rectangle
        surface.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}