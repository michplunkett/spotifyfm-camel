/** This abstract class serves as the basis for the top level of the Facade. */
package spotifyfmcamel.facades;

public abstract class Runner {
  abstract void createDataStores();

  abstract void setupRoutes() throws Exception;

  abstract void runMessengerSystem();

  abstract void calculateYearlyMoods();
}
