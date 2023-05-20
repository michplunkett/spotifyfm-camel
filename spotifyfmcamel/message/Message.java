/**
 * This Abstract Class serves as the baseline structure for all messages that will be handled within
 * this message delivery system.
 */
package spotifyfmcamel.message;

import java.util.ArrayList;

public abstract class Message {
  ArrayList<String> history = new ArrayList<>();

  public void addToHistory(String className) {
    history.add(className);
  }

  public void printHistory() {
    System.out.println("This message has been handled by these classes:");
    System.out.println(String.join(", ", history));
  }
}
