/**
 * This class is the top-level implementation of the Facade EIP for the application and is class
 * through which the whole application runs.
 */
package spotifyfmcamel.facades;

import java.util.ArrayList;
import spotifyfmcamel.calculations.WeekendMoodCalculator;
import spotifyfmcamel.calculations.WorkDayMoodCalculator;
import spotifyfmcamel.calculations.YearMoodCalculator;
import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;
import spotifyfmcamel.messages.SpotifyFMMessageIterator;

public class SpotifyFMRunner extends Runner {
  SpotifyFMRouteContainer routeContainer;
  LastFMTrackListingHandler trackListingHandler;

  public SpotifyFMRunner() throws Exception {
    createDataStores();
    setupRoutes();
    runMessengerSystem();
    calculateYearlyMoods();
  }

  void calculateYearlyMoods() {
    ArrayList<SpotifyFMMessage> messages = trackListingHandler.getTrackList();
    ArrayList<Integer> years = new ArrayList<>();
    SpotifyFMMessageIterator iterator = new SpotifyFMMessageIterator(messages);
    while (iterator.hasNext()) {
      Integer year = iterator.next().getListenDateTime().getYear();
      if (!years.contains(year)) {
        years.add(year);
      }
    }

    WeekendMoodCalculator weekend = new WeekendMoodCalculator();
    WorkDayMoodCalculator workday = new WorkDayMoodCalculator();
    YearMoodCalculator yearly = new YearMoodCalculator();

    for (Integer year : years) {
      weekend.calculate(messages, year);
      workday.calculate(messages, year);
      yearly.calculate(messages, year);
    }
  }

  void createDataStores() {
    SpotifyAudioFeatureHandler.getInstance();
    SpotifySearchStringHandler.getInstance();
    trackListingHandler = LastFMTrackListingHandler.getInstance();
  }

  void setupRoutes() throws Exception {
    routeContainer = new SpotifyFMRouteContainer();
  }

  void runMessengerSystem() throws Exception {
    routeContainer.runRoutes();
  }
}
