package spotifyfmcamel.facades;

import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;

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
    System.out.println(
        "This many tracks have been stored: " + trackListingHandler.getTrackList().size());
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
