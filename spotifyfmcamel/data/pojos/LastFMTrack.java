/**
 * This class represents the JSON structures present in the lastFMTrackListing.json
 * file.
 */
package spotifyfmcamel.data.pojos;

import com.google.gson.annotations.SerializedName;

public class LastFMTrack {
  @SerializedName("AlbumName")
  private String albumName;

  @SerializedName("Artist")
  private String artist;

  @SerializedName("ArtistUUID")
  private String artistUUID;

  @SerializedName("Duration")
  private int duration;

  @SerializedName("ListenDate")
  private String listenDate;

  @SerializedName("LowerCaseArtist")
  private String lowerCaseArtist;

  @SerializedName("Name")
  private String name;

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
}
