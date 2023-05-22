package spotifyfmcamel.facades;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import spotifyfmcamel.data.LastFMTrackListingHandler;
import spotifyfmcamel.data.SpotifyAudioFeatureHandler;
import spotifyfmcamel.data.SpotifySearchStringHandler;
import spotifyfmcamel.messages.SpotifyFMMessage;

public class SpotifyFMRouteContainer extends RouteContainer {
  // create CamelContext
  static final String brokerURL = "tcp://localhost:61616";
  static final String songQueue = "jms:queue:SPOTIFYFM_SONGSWITHINFO";
  static final String deadLetterQueue = "jms:queue:SPOTIFYFM_DEADLETTER";
  static final ObjectMapper mapper = new ObjectMapper();
  CamelContext context;

  // connect to ActiveMQ JMS broker listening on localhost on port 61616
  ConnectionFactory connectionFactory;

  public SpotifyFMRouteContainer() throws Exception {
    setUpConnection();
  }

  void setUpConnection() throws Exception {
    // create CamelContext
    context = new DefaultCamelContext();

    // connect to ActiveMQ JMS broker listening on localhost:61616
    connectionFactory = new ActiveMQConnectionFactory(brokerURL);
    context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

    context.addRoutes(
        new RouteBuilder() {
          public void configure() {
            // Read all files from the data/input folder
            from("file:data/input?noop=true")
                .errorHandler(
                    deadLetterChannel(deadLetterQueue)
                        .log("This message is being sent to the DeadLetter topic: ${body}"))
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .process(
                    new Processor() {
                      public void process(Exchange e) throws Exception {
                        SpotifySearchStringHandler searchStringHandler =
                            SpotifySearchStringHandler.getInstance();

                        String stringBody = mapper.writeValueAsString(e.getIn().getBody());
                        SpotifyFMMessage m = mapper.readValue(stringBody, SpotifyFMMessage.class);
                        searchStringHandler.getSongID(m);
                        if (m.getSpotifyID() == "") {
                          e.getIn().setBody(mapper.writeValueAsString(m));
                          throw new Exception();
                        }

                        SpotifyAudioFeatureHandler audioFeatureHandler =
                            SpotifyAudioFeatureHandler.getInstance();
                        boolean hasValence = audioFeatureHandler.getValence(m);
                        e.getIn().setBody(mapper.writeValueAsString(m));
                        if (!hasValence) {
                          throw new Exception();
                        }
                      }
                    })
                .to(songQueue);
            from(songQueue)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .choice()
                .when(body().contains("\"spotifyID\":\"\""))
                .to(deadLetterQueue)
                .when(body().contains("\"valence\":0.0"))
                .to(deadLetterQueue)
                .otherwise()
                .process(
                    new Processor() {
                      public void process(Exchange e) throws IOException {
                        LastFMTrackListingHandler trackListingHandler =
                            LastFMTrackListingHandler.getInstance();

                        String stringBody = mapper.writeValueAsString(e.getIn().getBody());
                        SpotifyFMMessage m = mapper.readValue(stringBody, SpotifyFMMessage.class);
                        trackListingHandler.addFullyQualifiedTrack(m);
                      }
                    })
                .endChoice();
            from(deadLetterQueue)
                .marshal()
                .json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .to("file:data/output/");
            try {
              Thread.sleep(5000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });
  }

  public void runRoutes() throws Exception {
    // start the route and let it do its work
    context.start();
    // I increased the sleep time on recommendation from Alan since I all of my messages
    // weren't going through when the value was 2000.
    Thread.sleep(10000);

    // stop the CamelContext
    context.stop();
  }
}
