package spotifyfmcamel.facades;

public abstract class RouteContainer {
  abstract void setUpConnection() throws Exception;

  public abstract void runRoutes() throws Exception;
}
