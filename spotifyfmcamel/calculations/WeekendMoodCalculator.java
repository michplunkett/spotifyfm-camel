/**
 * This class will calculate the average mood, valency score, of songs listened on weekend days
 * starting at 12 AM on Saturday morning and ending at 11:59 PM on Sunday night.
 */
package spotifyfmcamel.calculations;

import java.util.ArrayList;
import spotifyfmcamel.message.SpotifyFMMessage;

public class WeekendMoodCalculator extends MoodCalculatorStrategy {
  final String durationString = "Weekends in ";

  void calculate(ArrayList<SpotifyFMMessage> messages, String year) {
    ArrayList<SpotifyFMMessage> relevantMessages = getRelevantMessages(messages, year);
    double[] metrics = calculateMood(relevantMessages);
    printCalculation(durationString + year, metrics[0], metrics[1]);
  }

  public ArrayList<SpotifyFMMessage> getRelevantMessages(
      ArrayList<SpotifyFMMessage> messages, String year) {
    
  }
}
