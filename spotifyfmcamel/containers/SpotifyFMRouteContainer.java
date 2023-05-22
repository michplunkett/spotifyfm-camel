/** This class is the implementation of the Facade EIP for the Apache Camel routes. */
package spotifyfmcamel.containers;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import spotifyfmcamel.processors.AudioFeatureEnricher;
import spotifyfmcamel.processors.FullyQualifiedTrackProcessor;
import spotifyfmcamel.processors.SpotifySearchToIDEnricher;

public class SpotifyFMRouteContainer extends RouteContainer {
  // The strings are static and final because they are used multiple times and are essentially
  // configuration values.
  static final String brokerURL = "tcp://localhost:61616";
  static final String songQueue = "jms:queue:SPOTIFYFM_SONGSWITHINFO";
  static final String deadLetterQueue = "jms:queue:SPOTIFYFM_DEADLETTER";
  private CamelContext context;

  private ConnectionFactory connectionFactory;

  public void setUpConnection() throws Exception {
    // create CamelContext
    context = new DefaultCamelContext();

    // connect to ActiveMQ JMS broker listening on localhost:61616
    connectionFactory = new ActiveMQConnectionFactory(brokerURL);
    // Add the JMS component.
    context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

    context.addRoutes(
        new RouteBuilder() {
          public void configure() throws Exception {
            // Read all files from the data/input folder.
            // This is the first part of the Point-to-Point Channel EIP concept.
            from("file:data/input?noop=true")
                // Create the error handler and send any errored messages to the DeadLetter queue.
                .errorHandler(
                    // This is an implementation of the DeadLetter Channel EIP concept.
                    deadLetterChannel(deadLetterQueue)
                        .log("This message is being sent to the DeadLetter topic: ${body}"))
                .unmarshal()
                .json(JsonLibrary.Jackson)
                // Use the SpotifySearchToIDEnricher, which is an implementation of the
                // ContentEnricher EIP concept.
                .process(new SpotifySearchToIDEnricher())
                //  Use the AudioFeatureEnricher, which is an implementation of the ContentEnricher
                // EIP concept.
                .process(new AudioFeatureEnricher())
                .to(songQueue);
            // Create a Selective Consumer that only takes in messages with non-empty SpotifyIDs and
            // greater than 0.0 valence scores.
            // This is the second part of the Point-to-Point Channel EIP concept.
            from(songQueue)
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .filter(body().regex(".*((?!\"spotifyID\":\"\"|\"valence\":0.0).).*"))
                .process(new FullyQualifiedTrackProcessor());
            // Create a DeadLetter Channel consumer, which is an implementation of the Event-driven
            // Consumer EIP concept.
            from(deadLetterQueue)
                .marshal()
                .json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .to("file:data/output/");
            try {
              Thread.sleep(40000);
            } catch (InterruptedException e) {
              throw new Exception("The ActiveMQ queue was interrupted.");
            }
          }
        });
  }

  public void runRoutes() throws Exception {
    // Start the route and let it do its thing.
    context.start();

    // There are somewhere around 17k+ messages that will be going through the
    // system, so the sleep will be longer than the standard time.
    Thread.sleep(130000);

    // Stop the CamelContext.
    context.stop();
  }
}
