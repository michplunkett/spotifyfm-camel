package spotifyfmcamel;

import spotifyfmcamel.facades.SpotifyFMRouteContainer;

public class Main {
  public static void main(String args[]) throws Exception {
    SpotifyFMRouteContainer routeContainer = new SpotifyFMRouteContainer();
    routeContainer.runRoutes();
  }
}
