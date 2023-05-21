/** This class will calculate the average mood, valency score, for a given year. */
package spotifyfmcamel.calculations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import spotifyfmcamel.message.SpotifyFMMessage;
import spotifyfmcamel.message.SpotifyFMMessageIterator;

public class YearMoodCalculator extends MoodCalculatorStrategy {
  final String unitPrintString = "the year of ";

  void calculate(ArrayList<SpotifyFMMessage> messages, int year) {
    ArrayList<SpotifyFMMessage> relevantMessages = getRelevantMessages(messages, year);
    float[] metrics = calculateMood(relevantMessages);
    printCalculation(unitPrintString + year, metrics[0], metrics[1]);
  }

  public ArrayList<SpotifyFMMessage> getRelevantMessages(
      ArrayList<SpotifyFMMessage> messages, int year) {
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
}
