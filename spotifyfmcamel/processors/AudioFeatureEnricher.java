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
    if (!hasValence) {
      throw new Exception();
    }
  }
}
