package spotifyfmcamel.facades;

import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;

public class SpotifyFMDataContainer extends DataContainer {
  private SpotifyAudioFeatureHandler audioFeatureHandler;
  private SpotifySearchStringHandler searchStringHandler;

  public SpotifyFMDataContainer() {
    getDataHandlers();
  }

  void getDataHandlers() {
    // Needs error handling at some point
    audioFeatureHandler = SpotifyAudioFeatureHandler.getInstance();
    searchStringHandler = SpotifySearchStringHandler.getInstance();
  }
}
