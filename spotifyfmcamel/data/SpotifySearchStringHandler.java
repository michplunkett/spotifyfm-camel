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

  @Override
  void readInData() {
    try {
      // Instantiate FileReader for spotifySearchStringToSongID.json
      File file = new File("./data/input/spotifySearchStringToSongID.json");
      String jsonString = FileUtils.getContentsAsString(file);
      Gson gson = new Gson();

      Type gsonMap = new TypeToken<HashMap<String, String>>() {}.getType();
      searchStringToID = gson.fromJson(jsonString, gsonMap);
    } catch (FileNotFoundException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
