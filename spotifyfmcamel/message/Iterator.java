/** This abstract class serves as the basis for the SpotifyFMMessage iterator. */
package spotifyfmcamel.message;

public abstract class Iterator {
  abstract boolean hasNext();

  abstract Object next();
}
