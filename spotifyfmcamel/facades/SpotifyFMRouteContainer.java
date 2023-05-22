/** This class is the implementation of the Facade EIP for the Apache Camel routes. */
package spotifyfmcamel.facades;

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

  static final String brokerURL = "tcp://localhost:61616";
  static final String songQueue = "jms:queue:SPOTIFYFM_SONGSWITHINFO";
  static final String deadLetterQueue = "jms:queue:SPOTIFYFM_DEADLETTER";
  CamelContext context;

  ConnectionFactory connectionFactory;

  public void setUpConnection() throws Exception {
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
                .process(new SpotifySearchToIDEnricher())
                .process(new AudioFeatureEnricher())
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
                .process(new FullyQualifiedTrackProcessor())
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
