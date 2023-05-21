/**
 * This class is the implementation of Message.java that store all the information that is passed
 * via JMS.
 */
package spotifyfmcamel.message;

import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SpotifyFMMessage extends Message {
  DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @SerializedName("albumName")
  private String albumName;

  @SerializedName("artist")
  private String artist;

  @SerializedName("artistUUID")
  private String artistUUID;

  @SerializedName("duration")
  private int duration;

  @SerializedName("listenDate")
  private String listenDate;

  private LocalDateTime listenDateTime;

  @SerializedName("lowerCaseArtist")
  private String lowerCaseArtist;

  @SerializedName("name")
  private String name;

  @SerializedName("playCount")
  private int playCount;

  @SerializedName("rank")
  private int rank;

  @SerializedName("spotifyID")
  private String spotifyID;

  @SerializedName("acousticness")
  private float acousticness;

  @SerializedName("analysis_url")
  private String analysisUrl;

  @SerializedName("danceability")
  private float danceability;

  @SerializedName("duration_ms")
  private int durationMs;

  @SerializedName("energy")
  private float energy;

  @SerializedName("instrumentalness")
  private float instrumentalness;

  @SerializedName("key")
  private int key;

  @SerializedName("liveness")
  private float liveness;

  @SerializedName("loudness")
  private float loudness;

  @SerializedName("mode")
  private int mode;

  @SerializedName("speechiness")
  private float speechiness;

  @SerializedName("tempo")
  private float tempo;

  @SerializedName("time_signature")
  private int timeSignature;

  @SerializedName("track_href")
  private String trackHref;

  @SerializedName("uri")
  private String uri;

  @SerializedName("valence")
  private float valence;

  public String getAlbumName() {
    return albumName;
  }

  public String getArtist() {
    return artist;
  }

  public LocalDateTime getListenDateTime() {
    if (listenDateTime == null) {
      listenDateTime = LocalDateTime.parse(listenDate, localDateTimeFormatter);
    }

    return listenDateTime;
  }

  public String getName() {
    return name;
  }

  public String getSpotifyID() {
    return spotifyID;
  }

  public void setSpotifyID(String spotifyID) {
    this.spotifyID = spotifyID;
  }

  public float getValence() {
    return valence;
  }
}
