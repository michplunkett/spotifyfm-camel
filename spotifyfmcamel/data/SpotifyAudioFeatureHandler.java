/**
 * This class is the Singleton that will handle all the information in the
 * spotifyIDToAudioFeature.json file.
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
import spotifyfmcamel.data.pojos.SpotifyAudioFeatures;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class SpotifyAudioFeatureHandler extends DataHandler {
  private static SpotifyAudioFeatureHandler instance = null;

  private Map<String, SpotifyAudioFeatures> audioFeaturesMap;

  private SpotifyAudioFeatureHandler() throws Exception {
    readInData();
  }

  public static synchronized SpotifyAudioFeatureHandler getInstance() throws Exception {
    if (instance == null) {
      return instance = new SpotifyAudioFeatureHandler();
    }

    return instance;
  }

  // Function that adds the valence score to a message if its ID is contained in the
  // audioFeaturesMap map and returns true if a valence score was added.
  public boolean getValence(SpotifyFMMessage m) {
    String spotifyID = m.getSpotifyID();
    // Add class name to Message History.
    m.addToHistory(this.getClass().getName());
    if (audioFeaturesMap.containsKey(spotifyID)) {
      m.setValence(audioFeaturesMap.get(spotifyID).getValence());
      return true;
    }
    return false;
  }

  @Override
  void readInData() throws Exception {
    try {
      File file = new File("./data/stores/spotifyIDToAudioFeature.json");
      String jsonString = FileUtils.getContentsAsString(file);
      Gson gson = new Gson();

      Type gsonMap = new TypeToken<HashMap<String, SpotifyAudioFeatures>>() {}.getType();
      audioFeaturesMap = gson.fromJson(jsonString, gsonMap);
    } catch (FileNotFoundException e) {
      throw new Exception(e);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }
}
