/** This class is the Singleton that will hold all processed SpotifyFMMessages. */
package spotifyfmcamel.data;

import java.util.ArrayList;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class LastFMTrackListingHandler {
  private static LastFMTrackListingHandler instance = null;

  private ArrayList<SpotifyFMMessage> trackList;

  private LastFMTrackListingHandler() {
    trackList = new ArrayList<>();
  }

  public static synchronized LastFMTrackListingHandler getInstance() {
    if (instance == null) {
      return instance = new LastFMTrackListingHandler();
    }

    return instance;
  }

  // Add fully-qualified track to the trackList ArrayList.
  public void addFullyQualifiedTrack(SpotifyFMMessage m) {
    // Add class name to Message History.
    m.addToHistory(this.getClass().getName());
    trackList.add(m);
  }

  public ArrayList<SpotifyFMMessage> getTrackList() {
    return trackList;
  }
}
