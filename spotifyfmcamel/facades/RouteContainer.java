/**
 * This class represents the base structure for the SpotifyFMRouteContainer, which is part of the
 * Facade EIP.
 */
package spotifyfmcamel.facades;

public abstract class RouteContainer {
  abstract void setUpConnection() throws Exception;

  public abstract void runRoutes() throws Exception;
}
