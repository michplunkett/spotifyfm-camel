/**
 * This class represents the base structure for the SpotifyFMCalculationContainer, which is part of
 * the Facade EIP.
 */
package spotifyfmcamel.containers;

import java.util.ArrayList;

public abstract class CalculationContainer {

  abstract ArrayList<Integer> getRelevantYears();

  public abstract void calculateYearlyMoods();
}
