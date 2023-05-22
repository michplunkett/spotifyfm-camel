/**
 * This class will calculate the average mood, valency score, of songs listened to between the hours
 * of 9 AM to 5 PM on weekdays.
 */
package spotifyfmcamel.calculations;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class WorkDayMoodCalculator extends MoodCalculatorStrategy {
  static final String unitPrintString = "Workdays in ";
  static final LocalTime workStart = LocalTime.parse("09:00:00");
  static final LocalTime workEnd = LocalTime.parse("17:00:00");
  final ArrayList<DayOfWeek> weekdays =
      new ArrayList<>(
          Arrays.asList(
              DayOfWeek.MONDAY,
              DayOfWeek.TUESDAY,
              DayOfWeek.WEDNESDAY,
              DayOfWeek.THURSDAY,
              DayOfWeek.FRIDAY));

  // Function that determines if a given time is within the
  public static boolean isBetween(LocalTime testListenTime) {
    return testListenTime.isAfter(workStart) && testListenTime.isBefore(workEnd);
  }

  // Function that filters for messages with listenDates that were during the standard workday in a
  // given year.
  ArrayList<SpotifyFMMessage> getRelevantMessages(ArrayList<SpotifyFMMessage> messages, int year) {
    ArrayList<SpotifyFMMessage> relevantMessages = new ArrayList<>();
    SpotifyFMMessageIterator iterator = new SpotifyFMMessageIterator(messages);
    while (iterator.hasNext()) {
      SpotifyFMMessage m = iterator.next();
      LocalDateTime listenDateTime = m.getListenDateTime();
      if (listenDateTime.getYear() == year
          && weekdays.contains(listenDateTime.getDayOfWeek())
          && isBetween(listenDateTime.toLocalTime())) {
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
