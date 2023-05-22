/** This class will calculate the average mood, valency score, for a given year. */
package spotifyfmcamel.calculations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class YearMoodCalculator extends MoodCalculatorStrategy {
  static final String unitPrintString = "the year of ";

  // Function that gets all messages during a given year.
  ArrayList<SpotifyFMMessage> getRelevantMessages(ArrayList<SpotifyFMMessage> messages, int year) {
    ArrayList<SpotifyFMMessage> relevantMessages = new ArrayList<>();
    SpotifyFMMessageIterator iterator = new SpotifyFMMessageIterator(messages);
    while (iterator.hasNext()) {
      SpotifyFMMessage m = iterator.next();
      LocalDateTime listenDateTime = m.getListenDateTime();
      if (listenDateTime.getYear() == year) {
        relevantMessages.add(m);
      }
    }

    return relevantMessages;
  }

  void printCalculation(float mean, float standardDeviation, int year) {
    String durationString = unitPrintString + year;
    System.out.printf("The average valence score for %s is %f.\n", durationString, mean);
    System.out.printf(
        "The valence score standard deviation for %s is %f.\n", durationString, standardDeviation);
  }
}
