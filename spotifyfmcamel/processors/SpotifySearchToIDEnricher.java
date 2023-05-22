/**
 * This class is an implementation of the Content Enricher EIP concept that adds a Spotify song ID
 * to a message.
 */
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

    // I used the mapper first to write the value as a string because the JSON values were not
    // coming with quotation marks around their keys without it.
    String stringBody = mapper.writeValueAsString(e.getIn().getBody());
    SpotifyFMMessage m = mapper.readValue(stringBody, SpotifyFMMessage.class);
    searchStringHandler.getSongID(m);
    e.getIn().setBody(mapper.writeValueAsString(m));
    // If there isn't a SpotifyID, throw an error and send the message to the DeadLetter Channel.
    if (m.getSpotifyID() == "") {
      throw new Exception();
    }
  }
}
