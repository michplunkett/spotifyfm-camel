package spotifyfmcamel.facades;

import java.util.ArrayList;

public abstract class CalculationContainer {

  abstract ArrayList<Integer> getRelevantYears();

  public abstract void calculateYearlyMoods();
}
