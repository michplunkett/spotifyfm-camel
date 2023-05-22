package spotifyfmcamel.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class FullyQualifiedTrackProcessor implements Processor {
  private static final ObjectMapper mapper = new ObjectMapper();

  public void process(Exchange e) throws IOException {
    LastFMTrackListingHandler trackListingHandler = LastFMTrackListingHandler.getInstance();

    String stringBody = mapper.writeValueAsString(e.getIn().getBody());
    SpotifyFMMessage m = mapper.readValue(stringBody, SpotifyFMMessage.class);
    trackListingHandler.addFullyQualifiedTrack(m);
  }
}
