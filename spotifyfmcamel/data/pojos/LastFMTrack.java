/**
 * LastFMTrack.java
 * This class represents the JSON structures present in the lastFMTrackListing.json file.
 */
package spotifyfmcamel.data.pojos;

// Generated with https://www.jsonschema2pojo.org/
public class LastFMTrack {
  private String albumName;
  private String artist;
  private String artistUUID;
  private int duration;
  private String listenDate;
  private String lowerCaseArtist;
  private String name;
  private int playCount;
  private int rank;
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
