/** This class represents the JSON structures present in the spotifyIDToAudioFeatures.json file. */
package spotifyfmcamel.data.pojos;

import com.google.gson.annotations.SerializedName;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class SpotifyAudioFeatures {
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

  @SerializedName("id")
  private String id;

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

  public float getAcousticness() {
    return acousticness;
  }

  public void setAcousticness(float acousticness) {
    this.acousticness = acousticness;
  }

  public String getAnalysisUrl() {
    return analysisUrl;
  }

  public void setAnalysisUrl(String analysisUrl) {
    this.analysisUrl = analysisUrl;
  }

  public float getDanceability() {
    return danceability;
  }

  public void setDanceability(float danceability) {
    this.danceability = danceability;
  }

  public int getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(int durationMs) {
    this.durationMs = durationMs;
  }

  public float getEnergy() {
    return energy;
  }

  public void setEnergy(float energy) {
    this.energy = energy;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public float getInstrumentalness() {
    return instrumentalness;
  }

  public void setInstrumentalness(float instrumentalness) {
    this.instrumentalness = instrumentalness;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public float getLiveness() {
    return liveness;
  }

  public void setLiveness(float liveness) {
    this.liveness = liveness;
  }

  public float getLoudness() {
    return loudness;
  }

  public void setLoudness(float loudness) {
    this.loudness = loudness;
  }

  public int getMode() {
    return mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  public float getSpeechiness() {
    return speechiness;
  }

  public void setSpeechiness(float speechiness) {
    this.speechiness = speechiness;
  }

  public float getTempo() {
    return tempo;
  }

  public void setTempo(float tempo) {
    this.tempo = tempo;
  }

  public int getTimeSignature() {
    return timeSignature;
  }

  public void setTimeSignature(int timeSignature) {
    this.timeSignature = timeSignature;
  }

  public String getTrackHref() {
    return trackHref;
  }

  public void setTrackHref(String trackHref) {
    this.trackHref = trackHref;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public float getValence() {
    return valence;
  }

  public void setValence(float valence) {
    this.valence = valence;
  }
}
