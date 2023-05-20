/**
 * This class will calculate the average mood, valency score, of songs listened to between the hours
 * of 9 AM to 5 PM on weekdays.
 */
package spotifyfmcamel.calculations;

import java.util.ArrayList;
import spotifyfmcamel.message.SpotifyFMMessage;

public class WorkDayMoodCalculator extends MoodCalculatorStrategy {
  final String durationString = "Workdays in ";

  void calculate(ArrayList<SpotifyFMMessage> messages, String year) {
    ArrayList<SpotifyFMMessage> relevantMessages = getRelevantMessages(messages, year);
    double[] metrics = calculateMood(relevantMessages);
    printCalculation(durationString + year, metrics[0], metrics[1]);
  }

  public ArrayList<SpotifyFMMessage> getRelevantMessages(
      ArrayList<SpotifyFMMessage> messages, String year) {

  }
}
