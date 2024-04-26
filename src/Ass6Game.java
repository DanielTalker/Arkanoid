// 322624206 Daniel Talker

import core.Counter;
import animation.AnimationRunner;
import game.GameFlow;
import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass6Game class represents the main class of the Arkanoid game.
 */
public class Ass6Game {

    /**
     * The main entry point of the Arkanoid game.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Create a list to store the level information
        List<LevelInformation> levelsInfo = new ArrayList<>();
        // Determine the number of levels from the command-line arguments
        int numLevels = args.length;

        // Populate the list of level information based on the command-line arguments
        for (int i = 0; i < numLevels; i++) {
            if (args[i].equals("1")) {
                levelsInfo.add(new DirectHit());
            }
            if (args[i].equals("2")) {
                levelsInfo.add(new WideEasy());
            }
            if (args[i].equals("3")) {
                levelsInfo.add(new Green3());
            } else {
                continue;
            }
        }
        // If no specific levels were chosen, add default levels to the list
        if (levelsInfo.isEmpty()) {
            levelsInfo.add(new DirectHit());
            levelsInfo.add(new WideEasy());
            levelsInfo.add(new Green3());
        }

        // Create a counter to keep track of the total score
        Counter totalScore = new Counter(0);
        // Create an AnimationRunner
        AnimationRunner runner = new AnimationRunner();

        // Create a GameFlow object to manage the game flow
        GameFlow gameFlow = new GameFlow(runner,
                runner.getGui().getKeyboardSensor(), totalScore);

        // Run the levels using the GameFlow object
        gameFlow.runLevels(levelsInfo);
    }
}
