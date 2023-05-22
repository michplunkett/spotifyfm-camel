/**
 * SpotifyFMMessageIterator is an implementation of the Concrete Iterator from the Iterator Pattern.
 */
package spotifyfmcamel.messages;

import java.util.ArrayList;

public class SpotifyFMMessageIterator extends Iterator {
  // ArrayLists were used due to the comparative ease of adding items after
  // ArrayList has been instantiated.
  ArrayList<SpotifyFMMessage> messages;
  int index = 0;

  public SpotifyFMMessageIterator(ArrayList<SpotifyFMMessage> messages) {
    this.messages = messages;
  }

  public SpotifyFMMessage next() {
    // Returns null if the index exceeds the size of the ArrayList.
    if (hasNext()) {
      return messages.get(index++);
    }
    return null;
  }

  public boolean hasNext() {
    return index < messages.size() && messages.get(index) != null;
  }
}
