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

public class SpotifyAudioFeatureHandler extends DataHandler {
  private static SpotifyAudioFeatureHandler instance = null;

  private Map<String, SpotifyAudioFeatures> audioFeaturesMap;

  private SpotifyAudioFeatureHandler() {
    readInData();
  }

  public static synchronized SpotifyAudioFeatureHandler getInstance() {
    if (instance == null) {
      return instance = new SpotifyAudioFeatureHandler();
    }

    return instance;
  }

  @Override
  void readInData() {
    try {
      File file = new File("./data/input/spotifyIDToAudioFeature.json");
      String jsonString = FileUtils.getContentsAsString(file);
      Gson gson = new Gson();

      Type gsonMap = new TypeToken<HashMap<String, SpotifyAudioFeatures>>() {}.getType();
      audioFeaturesMap = gson.fromJson(jsonString, gsonMap);
    } catch (FileNotFoundException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
