/** This abstract class serves as the basis for the SpotifyFMMessage iterator. */
package spotifyfmcamel.messages;

public abstract class Iterator {
  abstract boolean hasNext();

  abstract Object next();
}
