/** This abstract class serves as the basis for the top level of the Facade EIP. */
package spotifyfmcamel.facades;

public abstract class Runner {
  abstract void createDataStores();

  abstract void runMessengerSystem() throws Exception;

  abstract void calculateYearlyMoods();
}
