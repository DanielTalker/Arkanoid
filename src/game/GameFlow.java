
package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import core.Counter;
import levels.LevelInformation;
import java.util.List;

/**
 * The GameFlow class handles the flow of the game, including running multiple
 * levels and displaying end screens.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter totalScore;

    /**
     * Constructs a new GameFlow instance.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     * @param totalScore the counter for the total score
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter totalScore) {
        this.ar = ar;
        this.ks = ks;
        this.totalScore = totalScore;
    }

    /**
     * Instantiates a new Game flow.
     *
     * @param score the score
     */
    public GameFlow(Counter score) {
        this.totalScore = score;
    }

    /**
     * Runs the given list of levels.
     *
     * @param levels  the list of level information
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 0;
        for (LevelInformation levelInfo : levels) {
            i++;

            // Create sprite collection and game environment for the level
            SpriteCollection sprites = new SpriteCollection();
            GameEnvironment environment = new GameEnvironment();

            // Create a new GameLevel instance with the necessary parameters
            GameLevel level = new GameLevel(sprites, environment, this.ar.getGui(),
                    levelInfo, this.ar, this.totalScore);

            // Initialize the level
            level.initialize();

            // Run the level as long as there are remaining blocks and balls
            while (level.getRemainingBlocks().getValue() != 0
                    && level.getRemainingBalls().getValue() != 0) {
                level.run();
            }

            // Check if the player lost (no remaining balls)
            if (level.getRemainingBalls().getValue() == 0) {
                Boolean loss = false;
                // Display the end screen with the loss message and total score
                this.ar.run(new KeyPressStoppableAnimation(this.ks,
                        KeyboardSensor.SPACE_KEY, new EndScreen(loss, this.totalScore)));
                // Close the GUI window
                this.ar.getGui().close();
            }

            int numLevels = levels.size();
            Boolean victory = true;
            // Check if the player won (all blocks destroyed) and it's the last level
            if (level.getRemainingBlocks().getValue() == 0 && i == numLevels) {
                // Display the end screen with the victory message and total score
                this.ar.run(new KeyPressStoppableAnimation(this.ks,
                        KeyboardSensor.SPACE_KEY, new EndScreen(victory, this.totalScore)));
                // Close the GUI window
                this.ar.getGui().close();
            }

        }
    }
}
