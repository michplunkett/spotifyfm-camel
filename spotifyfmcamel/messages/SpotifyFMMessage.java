/**
 * This class is the implementation of Message.java that store all the information that is passed
 * via JMS.
 */
package spotifyfmcamel.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "albumName",
  "artist",
  "artistUUID",
  "duration",
  "listenDate",
  "lowerCaseArtist",
  "name",
  "playCount",
  "rank",
  "spotifyID",
  "acousticness",
  "analysis_url",
  "danceability",
  "duration_ms",
  "energy",
  "id",
  "instrumentalness",
  "key",
  "liveness",
  "loudness",
  "mode",
  "speechiness",
  "tempo",
  "time_signature",
  "track_href",
  "uri",
  "valence"
})
public class SpotifyFMMessage extends Message implements Serializable {
  DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  @JsonProperty("albumName")
  private String albumName;

  @JsonProperty("artist")
  private String artist;

  @JsonProperty("artistUUID")
  private String artistUUID;

  @JsonProperty("duration")
  private int duration;

  @JsonProperty("listenDate")
  private String listenDate;

  private LocalDateTime listenDateTime;

  @JsonProperty("lowerCaseArtist")
  private String lowerCaseArtist;

  @JsonProperty("name")
  private String name;

  @JsonProperty("playCount")
  private int playCount;

  @JsonProperty("rank")
  private int rank;

  @JsonProperty("spotifyID")
  private String spotifyID;

  @JsonProperty("acousticness")
  private float acousticness;

  @JsonProperty("analysisUrl")
  private String analysisUrl;

  @JsonProperty("danceability")
  private float danceability;

  @JsonProperty("durationMs")
  private int durationMs;

  @JsonProperty("energy")
  private float energy;

  @JsonProperty("instrumentalness")
  private float instrumentalness;

  @JsonProperty("key")
  private int key;

  @JsonProperty("liveness")
  private float liveness;

  @JsonProperty("loudness")
  private float loudness;

  @JsonProperty("mode")
  private int mode;

  @JsonProperty("speechiness")
  private float speechiness;

  @JsonProperty("tempo")
  private float tempo;

  @JsonProperty("timeSignature")
  private int timeSignature;

  @JsonProperty("trackHref")
  private String trackHref;

  @JsonProperty("uri")
  private String uri;

  @JsonProperty("albumName")
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
