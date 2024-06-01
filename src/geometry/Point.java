
package geometry;

/**
 * The type Point.
 * The Point class represents a point in 2D space with x and y coordinates.
 * Points can be instantiated using the constructor, and their x and y coordinates
 * can be accessed using the getX() and getY() methods. The distance between two
 * points can be calculated using the distance() method. The equals() method can
 * be used to compare two points for equality.
 */
public class Point {
    // the x-coordinate of this point
    private double x;
    // the y-coordinate of this point
    private double y;

    /**
     * Instantiates a new Point.
     * constructor: initializes the x and y coordinates of the point
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between one point and another according to the
     * formula: the distance between two points (x1,y1) and (x2,y2) is the
     * square root of: ((x1-x2)*(x1-x2))+((y1- y2)*(y1-y2)).
     *
     * @param other the other point to calculate the distance to
     * @return the distance (double) of this point to the other point
     */
    public double distance(Point other) {
        // Calculates the difference between the x-coordinates of the two points
        double dx = this.x - other.getX();
        // Calculates the difference between the y-coordinates of the two points
        double dy = this.y - other.getY();
        // Calculates the distance using the Pythagorean theorem, and return it
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Threshold boolean.
     * This method compares two double values and determines if they are
     * within a small threshold value of each other.
     *
     * @param doubleNum1 the first double value to be compared
     * @param doubleNum2 the second double value to be compared
     * @return true if the absolute difference between val1 and val2 is less
     * than a small threshold value; false otherwise
     */
    public boolean threshold(double doubleNum1, double doubleNum2) {
        // Set a small threshold value
        double epsilon = 0.00001;
        // if the second double bigger
        if (doubleNum2 > doubleNum1) {
            return Math.abs(doubleNum2 - doubleNum1) < epsilon;
        }
        /* Compare the difference between the two input values to the threshold
        value. If the difference is smaller than the threshold value,
        return true, indicating that they are considered equal */
        return Math.abs(doubleNum1 - doubleNum2) < epsilon;
    }

    /**
     * Equals boolean.
     * checks whether this point is equal to another point.
     *
     * @param other the other point to compare to
     * @return a boolean value: true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        // returns false if the other point is null
        if (other == null) {
            return false;
        }
        // returns true if the x and y coordinates of both points are equal
        return threshold(this.x, other.getX())
                && threshold(this.y, other.getY());
    }

    /**
     * Gets x.
     *
     * @return the x-coordinate value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y-coordinate value of this point
     */
    public double getY() {
        return this.y;
    }
}
