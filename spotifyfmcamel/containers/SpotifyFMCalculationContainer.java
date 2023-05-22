/** This class is the implementation of the Facade EIP for the mood calculations. */
package spotifyfmcamel.containers;

import java.util.ArrayList;
import java.util.Collections;
import spotifyfmcamel.calculations.WeekendMoodCalculator;
import spotifyfmcamel.calculations.WorkDayMoodCalculator;
import spotifyfmcamel.calculations.YearMoodCalculator;
import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class SpotifyFMCalculationContainer extends CalculationContainer {
  // Instantiating all needed objects for the container.
  private final LastFMTrackListingHandler trackListingHandler =
      LastFMTrackListingHandler.getInstance();

  private final WeekendMoodCalculator weekend = new WeekendMoodCalculator();
  private final WorkDayMoodCalculator workday = new WorkDayMoodCalculator();
  private final YearMoodCalculator yearly = new YearMoodCalculator();

  // Collecting all unique years present in the track list.
  ArrayList<Integer> getRelevantYears() {
    ArrayList<SpotifyFMMessage> messages = trackListingHandler.getTrackList();
    ArrayList<Integer> years = new ArrayList<>();
    SpotifyFMMessageIterator iterator = new SpotifyFMMessageIterator(messages);
    while (iterator.hasNext()) {
      Integer year = iterator.next().getListenDateTime().getYear();
      if (!years.contains(year)) {
        years.add(year);
      }
    }

    Collections.sort(years);
    return years;
  }

  // Running the messages through the three strategies.
  public void calculateYearlyMoods() {
    ArrayList<SpotifyFMMessage> messages = trackListingHandler.getTrackList();

    ArrayList<Integer> years = getRelevantYears();
    System.out.println("Calculating moods based off of " + messages.size() + " tracks.");
    for (Integer year : years) {
      weekend.calculate(messages, year);
      workday.calculate(messages, year);
      yearly.calculate(messages, year);
    }
  }
}
