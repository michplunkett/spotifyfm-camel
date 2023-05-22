/**
 * This Interface class serves as baseline structure for the Strategies that will be implemented in
 * the other _Calculator classes.
 */
package spotifyfmcamel.calculations;

import java.util.ArrayList;
import spotifyfmcamel.messages.SpotifyFMMessage;

public abstract class MoodCalculatorStrategy {

  // Function signature for the mood calculation.
  public abstract void calculate(ArrayList<SpotifyFMMessage> messages, int year);

  // Function signature for getting the relevant messages for a given timeframe.
  abstract ArrayList<SpotifyFMMessage> getRelevantMessages(
      ArrayList<SpotifyFMMessage> messages, int year);

  float[] calculateMood(ArrayList<SpotifyFMMessage> messages) {
    float sum = 0.0F;
    float meanDiffs = 0.0F;
    int length = messages.size();

    // Exits the function early if there are not any messages to prevent a divide by 0 error.
    if (length == 0) {
      return new float[] {0.0F, 0.0F};
    }

    for (SpotifyFMMessage m : messages) {
      sum += m.getValence();
    }

    float mean = (sum / length);

    for (SpotifyFMMessage m : messages) {
      meanDiffs += Math.pow(m.getValence() - mean, 2);
    }
    float standardDeviation = (float) Math.sqrt(meanDiffs / length);

    return new float[] {mean, standardDeviation};
  }

  // Print function for all Strategies.
  void printCalculation(String durationString, float mean, float standardDeviation) {
    System.out.printf("The average valence score for %s is %f.\n", durationString, mean);
    System.out.printf(
        "The valence score standard deviation for %s is %f.\n", durationString, standardDeviation);
  }
}
