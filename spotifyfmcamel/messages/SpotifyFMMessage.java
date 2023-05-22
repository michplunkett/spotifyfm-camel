/**
 * This class is the implementation of Message.java that store all the information that is passed
 * via JMS.
 */
package spotifyfmcamel.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
  "valence"
})
@JsonIgnoreProperties(value = { "listenDateTime" })
public class SpotifyFMMessage extends Message {
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

  @JsonProperty("valence")
  private float valence;

  public String getAlbumName() {
    return albumName;
  }

  public String getArtist() {
    return artist;
  }

  public LocalDateTime getListenDateTime() {
    return LocalDateTime.parse(listenDate, localDateTimeFormatter);
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

  public void setValence(float valence) {
    this.valence = valence;
  }
}
