package spotifyfmcamel.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import spotifyfmcamel.data.SpotifySearchStringHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class SpotifySearchToIDEnricher implements Processor {
  private static final ObjectMapper mapper = new ObjectMapper();
  public void process(Exchange e) throws Exception {
    SpotifySearchStringHandler searchStringHandler = SpotifySearchStringHandler.getInstance();

    String stringBody = mapper.writeValueAsString(e.getIn().getBody());
    SpotifyFMMessage m = mapper.readValue(stringBody, SpotifyFMMessage.class);
    searchStringHandler.getSongID(m);
    e.getIn().setBody(mapper.writeValueAsString(m));
    if (m.getSpotifyID() == "") {
      throw new Exception();
    }
  }
}
