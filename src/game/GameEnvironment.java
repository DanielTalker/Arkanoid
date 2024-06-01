
package game;

import core.Collidable;
import core.CollisionInfo;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents a collection of Collidable objects and
 * provides methods for checking collisions with a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game environment with an empty list of collidables.
     */
    public GameEnvironment() {
        // Create a new ArrayList to store the collidables
        collidables = new ArrayList<>();
    }

    /**
     * Add a collidable object to the list of collidables.
     *
     * @param c the collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        // Add the given collidable to the list of collidables
        this.collidables.add(c);
    }

    /**
     * Removes the specified collidable from the game.
     *
     * @param c The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        // Remove the given collidable to the list of collidables
        this.collidables.remove(c);
    }

    /**
     * Gets the closest collision information between the given trajectory and
     * any collidable in the environment.
     *
     * @param trajectory the trajectory line to check for collision with the collidables
     * @return the closest collision information, or null if there is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Create a new ArrayList to store the collision information
        List<CollisionInfo> collisionList = new ArrayList<>();

        /* Check each collidable in the list for collisions with the
        given trajectory */
        for (Collidable c : this.collidables) {
            /* Calculate the closest intersection point between the
            collidable and the trajectory */
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            // If there is a collision, add the collision information to the list
            if (p != null) {
                collisionList.add(new CollisionInfo(p, c));
            }
        }

        // If there are no collisions, return null
        if (collisionList.isEmpty()) {
            return null;
        }

        /* Find the closest collision by comparing the distance from the start
        of the trajectory to each collision point */
        CollisionInfo closest = collisionList.get(0);
        for (int i = 1; i < collisionList.size(); i++) {
            CollisionInfo current = collisionList.get(i);
            double currentDistance
                    = trajectory.start().distance(current.collisionPoint());
            double smallestDistance
                    = trajectory.start().distance(closest.collisionPoint());
            /* If the current distance is shorter, change the closestPoint to
            current point */
            if (currentDistance < smallestDistance) {
                closest = current;
            }
        }

        // Return the collision information for the closest collision
        return closest;
    }

    /**
     * Gets the list of collidables in the environment.
     *
     * @return the list of collidables in the environment.
     */
    public List<Collidable> getCollidableList() {
        return this.collidables;
    }

}
