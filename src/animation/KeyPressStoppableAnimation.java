
package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The KeyPressStoppableAnimation class wraps an existing animation and allows
 * it to be stopped by a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Creates a new KeyPressStoppableAnimation instance with the specified parameters.
     *
     * @param sensor the KeyboardSensor used to detect key presses
     * @param key the key that stops the animation
     * @param animation the animation to be wrapped and controlled
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Performs one frame of the key press stoppable animation.
     * Delegates the frame rendering to the wrapped animation.
     * Checks if the specified key is pressed and sets the stop flag accordingly.
     *
     * @param d the DrawSurface to draw the animation frame on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (sensor.isPressed(key)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Determines whether the key press stoppable animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
