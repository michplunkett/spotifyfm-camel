/**
 * This Interface class serves as baseline structure for the Strategies that will be implemented in
 * the other _Calculator classes.
 */
package spotifyfmcamel.calculations;

import java.util.ArrayList;
import spotifyfmcamel.message.SpotifyFMMessage;

public abstract class MoodCalculatorStrategy {

  abstract void calculate(ArrayList<SpotifyFMMessage> messages, String year);

  abstract ArrayList<SpotifyFMMessage> getRelevantMessages(
      ArrayList<SpotifyFMMessage> messages, String year);

  public double[] calculateMood(ArrayList<SpotifyFMMessage> messages) {
    double sum = 0.0;
    final double meanDiffs = 0.0;
    int length = messages.size();

    for (SpotifyFMMessage m : messages) {
      sum += m.getValence();
    }

    double mean = sum / length;

    for (SpotifyFMMessage m : messages) {
      meanDiffs += Math.pow(m.getValence() - mean, 2);
    }
    double standardDeviation = Math.sqrt(meanDiffs / length);

    return new double[] {mean, standardDeviation};
  }

  public void printCalculation(String durationString, double mean, double standardDeviation) {
    System.out.printf("The average valence score for %s is %f.\n", durationString, mean);
    System.out.printf(
        "The valence score standard deviation for %s is %f.\n", durationString, standardDeviation);
  }
}
