/**
 * This class is an implementation of the Content Enricher EIP concept that adds a valence score to
 * a message.
 */
package spotifyfmcamel.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class AudioFeatureEnricher implements Processor {
  private static final ObjectMapper mapper = new ObjectMapper();

  public void process(Exchange e) throws Exception {
    SpotifyAudioFeatureHandler audioFeatureHandler = SpotifyAudioFeatureHandler.getInstance();

    SpotifyFMMessage m = mapper.readValue(e.getIn().getBody().toString(), SpotifyFMMessage.class);

    boolean hasValence = audioFeatureHandler.getValence(m);
    e.getIn().setBody(mapper.writeValueAsString(m));
    // If there isn't a valence score, throw an error and send the message to the DeadLetter
    // Channel.
    if (!hasValence) {
      throw new Exception();
    }
  }
}
