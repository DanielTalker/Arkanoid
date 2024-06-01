
package geometry;
import java.util.List;

/**
 * The Line class represents a line in two-dimensional Euclidean space.
 * A line is defined by its starting and ending points.
 * The class provides methods to calculate the length and midpoint of the line,
 * to access the starting and ending points of the line,
 * and to check if the line intersects with another line
 * and calculate the intersection point.
 */
public class Line {
    private Point start;
    private Point end;
    private static final double EPSILON = 0.00001;

    /**
     * Instantiates a new Line.
     * Constructs a new Line object with the given starting and ending points.
     *
     * @param start the starting point of the line
     * @param end   the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     * Constructs a new Line object with the given coordinates of starting
     * and ending points.
     *
     * @param x1 (double) the x-coordinate of the starting point of the line
     * @param y1 (double) the y-coordinate of the starting point of the line
     * @param x2 (double) the x-coordinate of the ending point of the line
     * @param y2 (double) the y-coordinate of the ending point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Length double.
     * Returns the length of this Line object, calculated as the Euclidean
     * distance between its starting and ending points.
     *
     * @return the length (double) of this Line object
     */
    public double length() {
        /* Calculating the length of the line by using the distance function
        from the Point class - finding the length by calculating the distance
        between the starting point and the ending point of the line */
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     * Calculates and returns the midpoint of this Line object as a new
     * Point object.
     *
     * @return the midpoint of this Line object as a new Point object
     */
    public Point middle() {
        // Calculate the x-coordinate of the midpoint by averaging the
        // x-coordinates of the start and end points
        double xMid = (this.start.getX() + this.end.getX()) / 2.0;
        // Calculate the y-coordinate of the midpoint by averaging the
        // y-coordinates of the start and end points
        double yMid = (this.start.getY() + this.end.getY()) / 2.0;
        // Create a new Point object with the calculated x and y coordinates
        // of the midpoint and return it as the result of the method
        return new Point(xMid, yMid);
    }

    /**
     * Start point.
     * This method returns the starting point of this Line object as a Point
     * object. The starting point is represented by the "start" instance
     * variable of the Line object. This method simply returns this variable.
     *
     * @return the starting point of this Line object
     */
    public Point start() {
        return this.start;
    }

    /**
     * This method returns the ending point of this Line object as a Point
     * object. The ending point is represented by the "end" instance variable
     * of the Line object. This method simply returns this variable.
     *
     * @return the ending point of this Line object
     */
    public Point end() {
        return this.end;
    }

    /**
     * This method checks if this Line object intersects with the given Line
     * object. In order to find if the point exist, the method uses the
     * equation of the line: y=mx+b, it finds the slope value and the b value
     * (y-intercept) of the two lines and compares them. Finally, the function
     * checks that the found point is within the range of the two lines
     * according to start and end points.
     *
     * @param other the other Line object to check for intersection with
     * @return a boolean value: true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        /* Initialize variables for holding the maximum and minimum y-values of
        the start and end points of the two lines within a certain epsilon */
        double minFirstY = Math.min(this.start.getY(), this.end.getY()) - EPSILON;
        double maxFirstY = Math.max(this.start.getY(), this.end.getY()) + EPSILON;
        double minSecondY = Math.min(other.start.getY(), other.end.getY()) - EPSILON;
        double maxSecondY =  Math.max(other.start.getY(), other.end.getY()) + EPSILON;

        /* Initialize variables for holding the maximum and minimum x-values of
        the start and end points of the two lines within a certain epsilon */
        double minSecondX = Math.min(other.start.getX(), other.end.getX()) - EPSILON;
        double maxSecondX = Math.max(other.start.getX(), other.end.getX()) + EPSILON;
        double minFirstX = Math.min(this.start.getX(), this.end.getX()) - EPSILON;
        double maxFirstX = Math.max(this.start.getX(), this.end.getX()) + EPSILON;

        // If the two lines are the same, they are definitely intersecting
        if (this.equals(other)) {
            return true;
        }

        // If the two lines have a common endpoint, they are intersecting
        if (this.start.equals(other.start) || this.end.equals(other.end)
                || this.start.equals(other.end) || this.end.equals(other.start)) {
            return true;
        }

        /* Check if both lines are on the same vertical line (within a certain
        threshold)- parallel to the y-axis, and both lines have the same
        x value, they are intersecting lines only if the y-value of the start
        or end point of one of the lines is contained in the other line */
        if (threshold(this.start.getX(), this.end.getX())
                && threshold(other.start.getX(), other.end.getX())
                && threshold(this.start.getX(), other.start.getX())) {
            if (this.start.getY() <= maxSecondY
                    && this.start.getY() >= minSecondY) {
                return true;
            } else {
                return (this.end.getY() <= maxSecondY
                        && this.end.getY() >= minSecondY);
            }
        }
        /* If both are vertical line, but each line has a different x value,
        they can't be intersecting */
        if (threshold(this.start.getX(), this.end.getX())
            && threshold(other.start.getX(), other.end.getX())) {
                return false;
        }

        // Calculate the horizontal and vertical distances between the
        // endpoints of the two lines
        double dx1 = this.end.getX() - this.start.getX();
        double dy1 = this.end.getY() - this.start.getY();
        double dx2 = other.end.getX() - other.start.getX();
        double dy2 = other.end.getY() - other.start.getY();

        //If only the other line is parallel to the y-axis
        if (threshold(other.start.getX(), other.end.getX())) {
            /* Finding the incline and the 'b' value (the y-intercept) of the
            first line (by isolate him: b=y-mx), in order to find the
            y-value of the intercept */
            double firstIncline = dy1 / dx1;
            double firstB = this.start.getY() - (firstIncline * this.start.getX());
            double foundX = other.start.getX();
            double foundY = (firstIncline * foundX) + firstB;
            /* Checking whether the y value of the intersection point found is
            within the range of the 2 lines (between the start point and the
            end point of both), and whether the x value found is contained in
            the line that is not parallel to the Y axis */
            if (foundY >= minFirstY && foundY <= maxFirstY
                    && foundY >= minSecondY && foundY <= maxSecondY
                    && foundX >= minFirstX && foundX <= maxFirstX) {
                return true;
            }
            // If the condition above is not met, the lines do not intersect
            return false;
        }

        // If only the first line is parallel to the y-axis
        if (threshold(this.start.getX(), this.end.getX())) {
            /* Finding the incline and the 'b' value (the y-intercept) of the
            second line (by isolate him: b=y-mx), in order to find the
            y-value of the intercept */
            double secondIncline = dy2 / dx2;
            double secondB = other.start.getY() - (secondIncline * other.start.getX());
            double foundX = this.start.getX();
            double foundY = (secondIncline * foundX) + secondB;
            /* Checking whether the y value of the intersection point found is
            within the range of the 2 lines (between the start point and the
            end point of both), and whether the x value found is contained in
            the line that is not parallel to the Y axis */
            if (foundY >= minFirstY && foundY <= maxFirstY
                    && foundY >= minSecondY && foundY <= maxSecondY
                    && foundX >= minSecondX && foundX <= maxSecondX) {
                return true;
            }
            // If the condition above is not met, the lines do not intersect
            return false;
        }

        /* If none of the lines are parallel to the y-axis, calculate their
        inclines, knowing that nothing is divided by 0 (because they are not
        parallel to the y-axis) */
        double firstIncline = dy1 / dx1;
        double secondIncline = dy2 / dx2;
        if (threshold(firstIncline, secondIncline)) {
            /* Finding the 'b' values (the y-intercept) of the two lines
            (by isolate him: b=y-mx), in order to check if they have the same
            equation of a straight line */
            double firstB = this.start.getY() - (firstIncline * this.start.getX());
            double secondB = other.start.getY() - (secondIncline * other.start.getX());
            /* If the two lines have the same equation (same incline and same
            y-intercept), and the x-value of one of them is contained in the
            other, they intersect */
            if (threshold(firstB, secondB)) {
                if ((this.start.getX() >= minSecondX
                        && this.start.getX() <= maxSecondX)
                        || (this.end.getX() >= minSecondX
                        && this.end.getX() <= maxSecondX)) {
                    return true;
                }
            }
            /* If the two lines have the same incline, but have a different
            y-intercept value they do not intersect. Or, if the two lines have
            the same equation of a straight line, but they do not overlap in a
            certain segment, they do not intersect */
            return false;
        }

        // Calculate the intersection point of the two lines using determinants
        // and return true if it's within both lines
        double delta = dx1 * dy2 - dy1 * dx2;
        double dx3 = other.start().getX() - start.getX();
        double dy3 = other.start().getY() - start.getY();
        double firstContain = (dx3 * dy2 - dy3 * dx2) / delta;
        double secondContain = (dx3 * dy1 - dy3 * dx1) / delta;

        /* If the intersection point lies within both lines (firstContain and
        secondContain are both between 0 and 1), the lines intersect
        and the method returns true */
        return (firstContain >= 0 && firstContain <= 1
                && secondContain >= 0 && secondContain <= 1);
    }

    /**
     * The purpose of this method is to find the point of intersection between
     * In order to find the point, the method uses the equation of the line:
     * y=mx+b, it finds the slope value and the b value (y-intercept) of the
     * two lines and compares them. Finally, the function checks that the found
     * point is within the range of the two lines according to start and end
     * points. During the calculations, checks are made such as: are the lines
     * equal, are one or more lines parallel to the y-axis (in order not to
     * divide by 0). After all the tests, if a relevant point is found that
     * meets the requirements, it is returned.
     *
     * @param other the other line to calculate intersection with
     * @return the intersection point, or null if the lines do not intersect.
     */
    public Point intersectionWith(Line other) {
        // Declare variables for storing inclines, intercepts, and intersection point
        double firstIncline, secondIncline;
        double firstB, secondB;
        double foundX, foundY;
        /* Initialize variables for holding the maximum and minimum x and y-values
        of the start and end points of the two lines within a certain epsilon */
        double minFirstY = Math.min(this.start.getY(), this.end.getY()) - EPSILON;
        double maxFirstY = Math.max(this.start.getY(), this.end.getY()) + EPSILON;
        double minSecondY = Math.min(other.start.getY(), other.end.getY()) - EPSILON;
        double maxSecondY =  Math.max(other.start.getY(), other.end.getY()) + EPSILON;
        double minFirstX = Math.min(this.start.getX(), this.end.getX()) - EPSILON;
        double maxFirstX = Math.max(this.start.getX(), this.end.getX()) + EPSILON;
        double minSecondX = Math.min(other.start.getX(), other.end.getX()) - EPSILON;
        double maxSecondX = Math.max(other.start.getX(), other.end.getX()) + EPSILON;

        // Check if the lines intersect, if not, return null
        if (!this.isIntersecting(other)) {
            return null;
        }
        /* If the two lines are actually 2 points, and the points are also the
        same between the two lines - there is an intersection point */
        if (this.start.equals(this.end) && other.start.equals(other.end)
                && this.start.equals(other.start)) {
            return this.start;
        }
        /* Check if the lines are equal, if so, return null, because they have
        infinite intersection points */
        if (this.equals(other)) {
            return null;
        }
        // Checks if both lines parallel to y-axis
        if (threshold(other.start.getX(), other.end.getX())
                && threshold(this.start.getX(), this.end.getX())) {

            /* If the two lines parallel to the y-axis do not have the same
            x-value, they do not intersect */
            if (!(threshold(this.start.getX(), other.start.getX()))) {
                return null;
            }
            /* If the first line is actually a point, and it is contained in
            the second line - this is the point of intersection */
            if (this.start.equals(this.end) && this.start.getX() <= maxSecondX
                    && this.start.getX() >= minSecondX) {
                return this.start;
            }
            /* If the second line is actually a point, and it is contained in
            the first line - this is the point of intersection */
            if (other.start.equals(other.end) && other.start.getX() <= maxFirstX
                    && other.start.getX() >= minFirstX) {
                return other.start;
            }
            /* Checking whether there is one point of overlap at the ends of
            the segments using equalsEndpoints method */
            return equalsEndpoints(this, other, minFirstY, maxFirstY,
                    minSecondY, maxSecondY);
        }

        // Calculate differences in x and y coordinates between endpoints of
        // the current line and the argument line
        double dx1 = this.end.getX() - this.start.getX();
        double dy1 = this.end.getY() - this.start.getY();
        double dx2 = other.end().getX() - other.start().getX();
        double dy2 = other.end().getY() - other.start().getY();

        // A case where only the first line is parallel to the y-axis
        if (threshold(this.start.getX(), this.end.getX())
                && !threshold(other.start.getX(), other.end.getX())) {
            /* Finding the incline and the 'b' value (the y-intercept) of the
            second line (by isolate him: b=y-mx) */
            secondIncline = dy2 / dx2;
            secondB = other.start.getY() - (secondIncline * other.start.getX());
            /* Because the first line is parallel to the y-axis, its x-value is
            constant, and so is the x-value of the point of intersection */
            foundX = this.start.getX();
            /* Finding the y-value of the intersection point according to the
            equation of the straight line: y=mx+b */
            foundY = (secondIncline * foundX) + secondB;
            /* If the y value that is found is contained in the range of the
            two straight lines, and the x value found is contained in the
            line that is not parallel to the y-axis - this is the intersection
            point of the lines, and must be returned */
            if (foundY >= minFirstY && foundY <= maxFirstY
                    && foundY >= minSecondY && foundY <= maxSecondY
                    && foundX >= minSecondX && foundX <= maxSecondX) {
                return new Point(foundX, foundY);
            }
            /* If the y value that is found is not contained in the range of
            the two straight lines - null must be returned,
            because there is no intersection point in the required range */
            return null;
        }
        //A case where only the second line is parallel to the y-axis
        if (threshold(other.start.getX(), other.end.getX())
                && !threshold(this.start.getX(), this.end.getX())) {
            /* Finding the incline and the 'b' value (the y-intercept) of the
            first line (by isolate him: b=y-mx) */
            firstIncline = dy1 / dx1;
            firstB = this.start.getY() - (firstIncline * this.start.getX());
            /* Because the other line is parallel to the y-axis, its x-value is
            constant, and so is the x-value of the point of intersection */
            foundX = other.start.getX();
            /* Finding the y-value of the intersection point according to the
            equation of the straight line: y=mx+b */
            foundY = (firstIncline * foundX) + firstB;
            /* If the y value that found is contained in the range of the
            two straight lines, and the x value found is contained in the line
            that is not parallel to the y-axis - this is the intersection
            point of the lines, and must be returned */
            if (foundY >= minFirstY && foundY <= maxFirstY
                    && foundY >= minSecondY && foundY <= maxSecondY
                    && foundX >= minFirstX && foundX <= maxFirstX) {
                return new Point(foundX, foundY);
            }
            /* If the y value that is found is not contained in the range of
            the two straight lines - null must be returned, because there
            is no intersection point in the required range */
            return null;
        }
        /* calculate lines inclines, knowing that nothing is divided by 0
        (because they are not parallel to the y-axis) */
        firstIncline = dy1 / dx1;
        secondIncline = dy2 / dx2;
        // Check if the lines are parallel
        if (threshold(firstIncline, secondIncline)) {
            return parallelLines(this, other, minFirstX, maxFirstX,
                    minSecondX, maxSecondX);
        }

        /* Check if the endpoints of the current line are on the other line,
        and return if so, because the lines have different slopes and
        therefore are not contained within each other */
        if (this.start.equals(other.end) || this.start.equals(other.start)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }

        /* Calculate y-intercepts (the 'b' in the equation: y=mx+b),
        by isolate them: b=y-m*x */
        firstB = this.start.getY() - (firstIncline * this.start.getX());
        secondB = other.start.getY() - (secondIncline * other.start.getX());
        // Find the x value of intersection point: x=(b2-b1)/(m1-m2)
        foundX = (secondB - firstB) / (firstIncline - secondIncline);
        // Find the y value of intersection point: y=m1*x+b1
        foundY = (firstIncline * foundX) + firstB;
        // Return new Point object with calculated x and y coordinates
        return new Point(foundX, foundY);
    }

    /**
     * This method checking whether parallel lines have a point of overlap at
     * the ends of the segment (start or end), and the x-coordinates of the
     * other two points are not in the range of the second line
     * (which they are not on). If so, the lines have one point of
     * intersection, because they are not contained in each other.
     *
     * @param first the first Line object
     * @param second the second Line object
     * @param minFirstX the minimum x-coordinate of the first line
     * @param maxFirstX the maximum x-coordinate of the first line
     * @param minSecondX the minimum x-coordinate of the second line
     * @param maxSecondX the maximum x-coordinate of the second line
     * @return a Point object representing the intersection point of the two
     * lines, or null if there is no intersection or more than one intersection
     */
    private Point parallelLines(Line first, Line second,
                                double minFirstX, double maxFirstX,
                                double minSecondX, double maxSecondX) {
        /* If the starting point of the first is equal to the starting
            point of the second */
        if (first.start.equals(second.start)
                && !(first.end.getX() <= maxSecondX
                && first.end.getX() >= minSecondX)
                && !(second.end.getX() <= maxFirstX
                && second.end.getX() >= minFirstX)) {
            return this.start;
        }
            /* If the starting point of the first is equal to the ending
            point of the second */
        if (first.start.equals(second.end)
                && !(first.end.getX() <= maxSecondX
                && first.end.getX() >= minSecondX)
                && !(second.start.getX() <= maxFirstX
                && second.start.getX() >= minFirstX)) {
            return first.start;
        }
            /* If the ending point of the first is equal to the starting
            point of the second */
        if (first.end.equals(second.start)
                && !(first.start.getX() <= maxSecondX
                && first.start.getX() >= minSecondX)
                && !(second.end.getX() <= maxFirstX
                && second.end.getX() >= minFirstX)) {
            return first.end;
        }
            /* If the ending point of the first is equal to the ending
            point of the second */
        if (first.end.equals(second.end)
                && !(first.start.getX() <= maxSecondX
                && first.start.getX() >= minSecondX)
                && !(second.start.getX() <= maxFirstX
                && second.start.getX() >= minFirstX)) {
            return this.end;
        }
            /* If the two lines are parallel (have the same slope), but none
            of the above conditions are met, they do not have one
            intersection point */
        return null;
    }


    /**
     * This method takes two Line objects that parallel to the y-axis,
     * along with the minimum and maximum y-coordinates for each line, and
     * checks for an intersection between them.
     * If there is exactly one point of intersection between the lines, this
     * method returns that point. If there is no intersection or more than one
     * intersection, this method returns null.
     *
     * @param first the first Line object
     * @param second the second Line object
     * @param minFirstY the minimum y-coordinate of the first line
     * @param maxFirstY the maximum y-coordinate of the first line
     * @param minSecondY the minimum y-coordinate of the second line
     * @param maxSecondY the maximum y-coordinate of the second line
     * @return a Point object representing the intersection point of the two
     * lines, or null if there is no intersection or more than one intersection
     */
    private Point equalsEndpoints(Line first, Line second,
                                  double minFirstY, double maxFirstY,
                                  double minSecondY, double maxSecondY) {
        /* If the starting point of the first is equal to the starting
        point of the second */
        if (first.start.equals(second.start)
                && !(first.end.getY() <= maxSecondY
                && first.end.getY() >= minSecondY)
                && !(second.end.getY() <= maxFirstY
                && second.end.getY() >= minFirstY)) {
            return first.start;
        }
            /* If the starting point of the first is equal to the ending
            point of the second */
        if (first.start.equals(second.end)
                && !(first.end.getY() <= maxSecondY
                && first.end.getY() >= minSecondY)
                && !(second.start.getY() <= maxFirstY
                && second.start.getY() >= minFirstY)) {
            return first.start;
        }
            /* If the ending point of the first is equal to the starting
            point of the second */
        if (first.end.equals(second.start)
                && !(first.start.getY() <= maxSecondY
                && first.start.getY() >= minSecondY)
                && !(second.end.getY() <= maxFirstY
                && second.end.getY() >= minFirstY)) {
            return first.end;
        }
            /* If the ending point of the first is equal to the ending
            point of the second */
        if (first.end.equals(second.end)
                && !(first.start.getY() <= maxSecondY
                && first.start.getY() >= minSecondY)
                && !(second.start.getY() <= maxFirstY
                && second.start.getY() >= minFirstY)) {
            return first.end;
        }
            /* If the two lines are parallel to the y-axis, But there is no
            point of overlap between them according to the conditions
            above, there is no one point of intersection between them */
        return null;
    }

    /**
     * The method checks whether two straight lines are equal: equal starting
     * points and equal ending points, or the starting point of one is equal
     * to the end of another and the ending point of one is equal to the
     * beginning of another.
     *
     * @param other the line to compare to this line
     * @return true if the two lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // If the two lines have the same start and end points, they are equal
        if (start.equals(other.start) && end.equals(other.end)) {
            return true;
        }

        // If the two lines have the same endpoints in reverse order, they are equal
        if (start.equals(other.end) && end.equals(other.start)) {
            return true;
        }
        // If none of the above conditions are met, the lines are not equal
        return false;
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
        /* Compare the difference between the two input values to the threshold
        value. If the difference is smaller than the threshold value,
        return true, indicating that they are considered equal */
            return Math.abs(val2 - val1) < EPSILON;
        }
        // if the first double bigger
        return Math.abs(val1 - val2) < EPSILON;
    }

    /**
     * This method takes a rectangle as input and returns the closest
     * intersection point between the rectangle and a line, starting from
     * the line's start point.
     * If no intersection points are found, it returns null.
     *
     * @param rect the rectangle to check for intersection points with.
     * @return the closest intersection point to the start of the line, or null if no intersection points are found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        /* get a list of all the intersection points between the
        line and the rectangle */
        List<Point> intersectionPoints = rect.intersectionPoints(this);

        // if there are no intersection points, return null
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        // find the closest intersection point to the start of the line
        Point closestPoint = intersectionPoints.get(0);
        double closestDistance = this.start().distance(closestPoint);

        for (int i = 1; i < intersectionPoints.size(); i++) {
            Point currentPoint = intersectionPoints.get(i);
            double currentDistance = this.start().distance(currentPoint);

            /* If the current distance is shorter, change the value of the
            closest distance to it and change the closestPoint to
            current point */
            if (currentDistance < closestDistance) {
                closestPoint = currentPoint;
                closestDistance = currentDistance;
            }
        }

        // Return the closest intersection point found
        return closestPoint;
    }

}
