/**
 * This abstract class serves as the Template for the Singleton classes that will handle the JSON
 * files that will act as the data stores for this application.
 */
package spotifyfmcamel.data;

public abstract class DataHandler {

  abstract void readInData();
}
