/** This class will calculate the average mood, valency score, for a given year. */
package spotifyfmcamel.calculations;

import java.util.ArrayList;
import spotifyfmcamel.message.SpotifyFMMessage;

public class YearMoodCalculator extends MoodCalculatorStrategy {
  final String durationString = "the year of ";

  void calculate(ArrayList<SpotifyFMMessage> messages, String year) {
    ArrayList<SpotifyFMMessage> relevantMessages = getRelevantMessages(messages, year);
    double[] metrics = calculateMood(relevantMessages);
    printCalculation(durationString + year, metrics[0], metrics[1]);
  }

  public ArrayList<SpotifyFMMessage> getRelevantMessages(ArrayList<SpotifyFMMessage> messages, String year) {

  }
}
