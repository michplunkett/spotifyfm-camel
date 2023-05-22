package spotifyfmcamel.facades;

import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;

public class SpotifyFMRunner extends Runner {
  SpotifyFMRouteContainer routeContainer;

  public SpotifyFMRunner() throws Exception {
    createDataStores();
    setupRoutes();
  }

  void calculateYearlyMoods() {}

  void createDataStores() {
    SpotifyAudioFeatureHandler.getInstance();
    SpotifySearchStringHandler.getInstance();
  }

  void setupRoutes() throws Exception {
    routeContainer = new SpotifyFMRouteContainer();
  }

  void runMessengerSystem() {}
}
