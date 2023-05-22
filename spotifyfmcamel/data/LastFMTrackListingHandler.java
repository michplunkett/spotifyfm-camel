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

  public void addFullyQualifiedTrack(SpotifyFMMessage m) {
    m.addToHistory(this.getClass().getName());
    trackList.add(m);
  }

  public ArrayList<SpotifyFMMessage> getTrackList() {
    return trackList;
  }
}
