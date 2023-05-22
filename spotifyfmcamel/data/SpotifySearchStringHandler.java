/**
 * This class is the Singleton that will handle all the information in the
 * spotifySearchStringToSongID.json file.
 */
package spotifyfmcamel.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mchange.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class SpotifySearchStringHandler extends DataHandler {
  private static SpotifySearchStringHandler instance = null;

  private Map<String, String> searchStringToID;

  private SpotifySearchStringHandler() {
    readInData();
  }

  public static synchronized SpotifySearchStringHandler getInstance() {
    if (instance == null) {
      return instance = new SpotifySearchStringHandler();
    }

    return instance;
  }

  // Function that adds the SpotifyID to a message if its search string is contained in the
  // searchStringToID map and returns true if a SpotifyID was added.
  public boolean getSongID(SpotifyFMMessage m) {
    String key = m.getArtist() + " " + m.getAlbumName() + " " + m.getName();
    // Add class name to Message History.
    m.addToHistory(this.getClass().getName());
    if (searchStringToID.containsKey(key)) {
      m.setSpotifyID(searchStringToID.get(key));
      return true;
    }
    return false;
  }

  @Override
  void readInData() {
    try {
      File file = new File("./data/stores/spotifySearchStringToSongID.json");
      String jsonString = FileUtils.getContentsAsString(file);
      Gson gson = new Gson();

      Type gsonMap = new TypeToken<HashMap<String, String>>() {}.getType();
      searchStringToID = gson.fromJson(jsonString, gsonMap);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
