/** This abstract class serves as the basis for the top level of the Facade EIP. */
package spotifyfmcamel.containers;

public abstract class Runner {
  abstract void createDataStores() throws Exception;

  abstract void runMessengerSystem() throws Exception;

  abstract void calculateYearlyMoods();
}
