// 322624206 Daniel Talker

package animation;

import biuoop.DrawSurface;
import game.SpriteCollection;
import java.awt.Color;

/**
 * The CountdownAnimation class is responsible for displaying a countdown
 * animation on a DrawSurface.
 * It counts down from a given number of seconds and updates the game screen
 * (SpriteCollection) accordingly.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private long initiationTime;

    /**
     * Creates a new CountdownAnimation instance with the specified parameters.
     *
     * @param numOfSeconds the total number of seconds for the countdown
     * @param countFrom the number to start the count from
     * @param gameScreen   the SpriteCollection representing the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.initiationTime = System.currentTimeMillis();
    }

    /**
     * Performs one frame of the countdown animation.
     * Updates the game screen by drawing all sprites and the current count
     * number on the specified DrawSurface.
     * Updates the count and checks if the animation should stop based on the
     * elapsed time.
     *
     * @param d the DrawSurface to draw the animation frame on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        double numOfSec = this.numOfSeconds * 1000;
        int initialCount = this.countFrom + 1;

        Color numColor = new Color(181, 215, 78);
        d.setColor(numColor);
        //d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        this.gameScreen.drawAllOn(d);


        d.setColor(numColor);
        d.drawText(335, 400, Integer.toString(this.countFrom), 150);

        if (System.currentTimeMillis() -  this.initiationTime
                > numOfSec / initialCount) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }

        if (this.countFrom == 0) {
            this.running = false;
        }
    }

    /**
     * Determines whether the countdown animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}