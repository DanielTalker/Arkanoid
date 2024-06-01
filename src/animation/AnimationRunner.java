
package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running animations and
 * controlling their frame rate.
 * It uses a GUI object to display the animations on the screen.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Creates a new AnimationRunner instance with the specified GUI,
     * frames per second, and Sleeper.
     *
     * @param gui the GUI object to display animations
     * @param framesPerSecond the desired number of frames per second
     * @param sleeper the Sleeper object to control the frame rate
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Creates a new AnimationRunner instance with default values.
     * Uses a new GUI object with a window titled "Arkanoid" and dimensions 800x600,
     * sets the frames per second to 60, and uses a new Sleeper object.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     /**
     * Runs the specified Animation object until it should stop.
     * The animation's doOneFrame method is called repeatedly to update the
     * animation state and draw on the GUI.
     *
     * @param animation the Animation object to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            // Timing the frame
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            // Perform one frame of the animation
            animation.doOneFrame(d);

            // Display the updated frame on the GUI
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                // Sleep to control the frame rate
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns the Sleeper object used by the AnimationRunner.
     *
     * @return the Sleeper object
     */
    public Sleeper getSleeper() {
        return this.sleeper;
    }

    /**
     * Returns the GUI object used by the AnimationRunner.
     *
     * @return the GUI object
     */
    public GUI getGui() {
        return gui;
    }
}
