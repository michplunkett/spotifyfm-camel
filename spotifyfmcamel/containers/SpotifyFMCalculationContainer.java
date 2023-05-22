/** This class is the implementation of the Facade EIP for the mood calculations. */
package spotifyfmcamel.containers;

import java.util.ArrayList;
import spotifyfmcamel.calculations.WeekendMoodCalculator;
import spotifyfmcamel.calculations.WorkDayMoodCalculator;
import spotifyfmcamel.calculations.YearMoodCalculator;
import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class SpotifyFMCalculationContainer extends CalculationContainer {

  private final LastFMTrackListingHandler trackListingHandler =
      LastFMTrackListingHandler.getInstance();

  private final WeekendMoodCalculator weekend = new WeekendMoodCalculator();
  private final WorkDayMoodCalculator workday = new WorkDayMoodCalculator();
  private final YearMoodCalculator yearly = new YearMoodCalculator();

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

    return years;
  }

  public void calculateYearlyMoods() {
    ArrayList<SpotifyFMMessage> messages = trackListingHandler.getTrackList();

    ArrayList<Integer> years = getRelevantYears();
    for (Integer year : years) {
      weekend.calculate(messages, year);
      workday.calculate(messages, year);
      yearly.calculate(messages, year);
    }
  }
}
