/**
 * This class is the top-level implementation of the Facade EIP for the application and is class
 * through which the whole application runs.
 */
package spotifyfmcamel.facades;

import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;

public class SpotifyFMRunner extends Runner {
  SpotifyFMRouteContainer routeContainer;
  SpotifyFMCalculationContainer calculator;

  public SpotifyFMRunner() throws Exception {
    createDataStores();
    runMessengerSystem();
    calculateYearlyMoods();
  }

  void calculateYearlyMoods() {
    calculator = new SpotifyFMCalculationContainer();
    calculator.calculateYearlyMoods();
  }

  void createDataStores() {
    SpotifyAudioFeatureHandler.getInstance();
    SpotifySearchStringHandler.getInstance();
  }

  void runMessengerSystem() throws Exception {
    routeContainer.setUpConnection();
    routeContainer.runRoutes();
  }
}
