/**
 * This class is the Singleton that will handle all the information in the lastFMTrackListing.json
 * file.
 */
package spotifyfmcamel.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mchange.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import spotifyfmcamel.data.pojos.LastFMTrack;

public class LastFMTrackListingHandler extends DataHandler {
  private static LastFMTrackListingHandler instance = null;

  private ArrayList<LastFMTrack> trackList;

  private LastFMTrackListingHandler() {
    trackList = new ArrayList<>();
    readInData();
  }

  public static synchronized LastFMTrackListingHandler getInstance() {
    if (instance == null) {
      return instance = new LastFMTrackListingHandler();
    }

    return instance;
  }

  @Override
  void readInData() {
    try {
      // Instantiate FileReader for lastFMTrackListing.json
      File file = new File("./data/input/lastFMTrackListing.json");

      String jsonString = FileUtils.getContentsAsString(file);
      // Instantiate Gson
      Gson gson = new Gson();

      Type gsonList = new TypeToken<ArrayList<LastFMTrack>>() {}.getType();
      trackList = gson.fromJson(jsonString, gsonList);
    } catch (FileNotFoundException e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
