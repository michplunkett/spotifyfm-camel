package spotifyfmcamel;

import spotifyfmcamel.containers.SpotifyFMRunner;

public class Main {
  public static void main(String[] args) throws Exception {
    try {
      new SpotifyFMRunner();
    } catch (Exception e) {
      System.out.println(
          "The application ran into an error, please check your configuration to make sure everything is setup correctly.");
      System.out.println("This is the error that you received: " + e.getMessage());
    }
  }
}
