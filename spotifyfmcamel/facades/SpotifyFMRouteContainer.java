package spotifyfmcamel.facades;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;

public class SpotifyFMRouteContainer extends RouteContainer {
  // create CamelContext
  static final String brokerURL = "tcp://localhost:61616";
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
                .log("RETRIEVED:  ${file:onlyname}")
                .unmarshal()
                .json(JsonLibrary.Jackson)
                .to("jms:queue:SPOTIFYFM_UNFILTEREDSONGS");
            from("jms:queue:SPOTIFYFM_UNFILTEREDSONGS")
                .log("RETRIEVED: ${body} in SPOTIFYFM_UNFILTEREDSONGS queue")
                .process(new Processor() {
                  public void process(Exchange e) {
                    System.out.println("!!!!!!");
                    System.out.println(e.getIn().getBody());
                  }
                });
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
