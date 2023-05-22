package spotifyfmcamel.facades;

import java.util.ArrayList;

public abstract class Calculator {

  abstract ArrayList<Integer> getRelevantYears();

  public abstract void calculateYearlyMoods();
}
