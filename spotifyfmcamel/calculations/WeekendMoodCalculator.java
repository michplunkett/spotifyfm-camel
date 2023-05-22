/**
 * This class will calculate the average mood, valency score, of songs listened on weekend days
 * starting at 12 AM on Saturday morning and ending at 11:59 PM on Sunday night.
 */
package spotifyfmcamel.calculations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class WeekendMoodCalculator extends MoodCalculatorStrategy {
  static final String unitPrintString = "Weekends in ";
  final ArrayList<DayOfWeek> weekendDays =
      new ArrayList<>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

  void printCalculation(float mean, float standardDeviation, int year) {
    String durationString = unitPrintString + year;
    System.out.printf("The average valence score for %s is %f.\n", durationString, mean);
    System.out.printf(
        "The valence score standard deviation for %s is %f.\n", durationString, standardDeviation);
  }

  // Function that filters for messages with listenDates that were during the weekend in a given
  // year.
  ArrayList<SpotifyFMMessage> getRelevantMessages(ArrayList<SpotifyFMMessage> messages, int year) {
    ArrayList<SpotifyFMMessage> relevantMessages = new ArrayList<>();
    SpotifyFMMessageIterator iterator = new SpotifyFMMessageIterator(messages);
    while (iterator.hasNext()) {
      SpotifyFMMessage m = iterator.next();
      LocalDateTime listenDateTime = m.getListenDateTime();
      if (listenDateTime.getYear() == year && weekendDays.contains(listenDateTime.getDayOfWeek())) {
        relevantMessages.add(m);
      }
    }

    return relevantMessages;
  }
}
