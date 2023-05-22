/**
 * This abstract class serves as the baseline for the Singleton classes that will handle the JSON
 * files that will act as the data stores for this application.
 */
package spotifyfmcamel.data;

public abstract class DataHandler {

  abstract void readInData() throws Exception;
}
