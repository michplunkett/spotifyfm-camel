/** This class represents the JSON structures present in the lastFMTrackListing.json file. */
package spotifyfmcamel.data.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "AlbumName",
  "Artist",
  "ArtistUUID",
  "Duration",
  "ListenDate",
  "LowerCaseArtist",
  "Name",
  "PlayCount",
  "Rank",
  "SpotifyID"
})
@Generated("jsonschema2pojo")
public class LastFMTrack {

  @JsonProperty("AlbumName")
  private String albumName;

  @JsonProperty("Artist")
  private String artist;

  @JsonProperty("ArtistUUID")
  private String artistUUID;

  @JsonProperty("Duration")
  private int duration;

  @JsonProperty("ListenDate")
  private String listenDate;

  @JsonProperty("LowerCaseArtist")
  private String lowerCaseArtist;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("PlayCount")
  private int playCount;

  @JsonProperty("Rank")
  private int rank;

  @JsonProperty("SpotifyID")
  private String spotifyID;

  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getArtistUUID() {
    return artistUUID;
  }

  public void setArtistUUID(String artistUUID) {
    this.artistUUID = artistUUID;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getListenDate() {
    return listenDate;
  }

  public void setListenDate(String listenDate) {
    this.listenDate = listenDate;
  }

  public String getLowerCaseArtist() {
    return lowerCaseArtist;
  }

  public void setLowerCaseArtist(String lowerCaseArtist) {
    this.lowerCaseArtist = lowerCaseArtist;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPlayCount() {
    return playCount;
  }

  public void setPlayCount(int playCount) {
    this.playCount = playCount;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public String getSpotifyID() {
    return spotifyID;
  }

  public void setSpotifyID(String spotifyID) {
    this.spotifyID = spotifyID;
  }
}
