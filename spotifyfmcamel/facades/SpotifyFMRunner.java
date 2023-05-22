package spotifyfmcamel.facades;

public class SpotifyFMRunner extends Runner {
  SpotifyFMDataContainer dataContainer;
  SpotifyFMRouteContainer routeContainer;

  public SpotifyFMRunner() throws Exception {
    getDataStores();
    setupRoutes();
  }

  void calculateYearlyMoods() {}

  void getDataStores() {
    dataContainer = new SpotifyFMDataContainer();
  }

  void setupRoutes() throws Exception {
    routeContainer = new SpotifyFMRouteContainer();
  }

  void runMessengerSystem() {}
}
