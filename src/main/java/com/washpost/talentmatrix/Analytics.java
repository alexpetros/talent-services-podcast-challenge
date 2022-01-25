// Feel free to add additional imports as needed, but please do not remove any of these
import java.util.*;
import java.io.*;
import org.json.simple.*;

public class Analytics {

  // Information about a podcast can be fetched at WAPO_AUDIO_API_BASE_URL + episodeId
  private static String LOGS_FILE_PATH = "./data/logs.tsv";
  private static String WAPO_AUDIO_API_BASE_URL = "https://d1sk0wal7twks.cloudfront.net/api/v1/audio/";

  public void processLogs() {

    ArrayList<String[]> data = new ArrayList<>(); //initializing a new ArrayList out of String[]'s
    try (BufferedReader TSVReader = new BufferedReader(new FileReader(LOGS_FILE_PATH))) {
      String line = null;
      while ((line = TSVReader.readLine()) != null) {
        String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
        data.add(lineItems); //adding the splitted line array to the ArrayList
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println(data);


    // Your code goes here...
    // Feel free to add additional methods, etc. as necessary

    // To push an AnalyticsMessage object to the mocked Analytics API, use:
    // this.postAnalytics(message);
  }

  /**
   * Provided AnalyticsMessage class
   *
   * PLEASE DO NOT MODIFY
   */

  private static class AnalyticsMessage {
    private String ipAddress = null;
    private String userAgent = null;
    private String episodeId = null;
    private String title = null;

    public AnalyticsMessage() { }

    public AnalyticsMessage(String ipAddress, String userAgent, String episodeId, String title) {
      this.ipAddress = ipAddress;
      this.userAgent = userAgent;
      this.episodeId = episodeId;
      this.title = title;
    }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }

    public String getEpisodeId() { return episodeId; }
    public void setEpisodeId(String episodeId) { this.episodeId = episodeId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean equals(AnalyticsMessage other) {
      return (
        ((this.ipAddress == null && other.ipAddress == null) ||
          (this.ipAddress != null && other.ipAddress != null && this.ipAddress.equals(other.ipAddress))
        ) &&
        ((this.userAgent == null && other.userAgent == null) ||
          (this.userAgent != null && other.userAgent != null && this.userAgent.equals(other.userAgent))
        ) &&
        ((this.episodeId == null && other.episodeId == null) ||
          (this.episodeId != null && other.episodeId != null && this.episodeId.equals(other.episodeId))
        ) &&
        ((this.title == null && other.title == null) ||
          (this.title != null && other.title != null && this.title.equals(other.title))
        )
      );
    }

    public boolean equalsWithOnlyLogFields(AnalyticsMessage other) {
      return (
        ((this.ipAddress == null && other.ipAddress == null) ||
          (this.ipAddress != null && other.ipAddress != null && this.ipAddress.equals(other.ipAddress))
        ) &&
        ((this.userAgent == null && other.userAgent == null) ||
          (this.userAgent != null && other.userAgent != null && this.userAgent.equals(other.userAgent))
        ) &&
        ((this.episodeId == null && other.episodeId == null) ||
          (this.episodeId != null && other.episodeId != null && this.episodeId.equals(other.episodeId))
        )
      );
    }

    public String toString() {
      return String.format(
        "[ipAddress=%s, userAgent=%s, episodeId=%s, title=%s]",
        this.ipAddress,
        this.userAgent,
        this.episodeId,
        this.title
      );
    }
  }

  /**
   * Test code below
   *
   * PLEASE DO NOT MODIFY
   */

  public static void main(String[] args) throws Exception {
    System.out.println("ℹ️  running your code");
    Analytics analytics = new Analytics();
    analytics.processLogs();
    System.out.println("✅ provided code ran without errors");

    analytics.runTests();
  }

  private List<AnalyticsMessage> receivedMessages = new ArrayList<>();

  private void postAnalytics(AnalyticsMessage message) {
    this.receivedMessages.add(message);
  }

  private void runTests() {
    System.out.println("ℹ️  running tests");

    // Verify we received the right number of messages
    System.out.println("ℹ️  checking number of message received");
    assert receivedMessages.size() == getExpectedData().size() : String.format("Wrong number of analytics events received (expected %d, received %d)", getExpectedData().size(), receivedMessages.size());
    System.out.println("✅ correct number of analytics events received");

    // Verify fields extracted from log data
    System.out.println("ℹ️  checking fields extracted from the log data");
    assertEqualMessageListContents(getExpectedData(), receivedMessages, false);
    System.out.println("✅ extracted fields from log data correctly");

    // Verify fields extracted from Audio API
    System.out.println("ℹ️  checking fields extracted from the Audio API data");
    assertEqualMessageListContents(getExpectedData(), receivedMessages, true);
    System.out.println("✅ extracted fields from Audio API correctly");

    System.out.println("✨ All tests passed ✨");
  }

  private static void assertEqualMessageListContents(List<AnalyticsMessage> expected, List<AnalyticsMessage> received, boolean includeAudioApiData) {
    for (int i = 0; i < expected.size(); i++) {
      AnalyticsMessage e = expected.get(i);
      AnalyticsMessage r = received.get(i);

      boolean isEqual = includeAudioApiData ? e.equals(r) : e.equalsWithOnlyLogFields(r);

      assert isEqual : String.format("Values are not the same at index %d (expected %s, received %s)", i, e.toString(), r.toString());
    }
  }

  /**
   * Test fixture data below
   *
   * PLEASE DO NOT MODIFY
   */

  private static List<AnalyticsMessage> getExpectedData() {
    List<AnalyticsMessage> expected = new ArrayList<>();
    expected.add(new AnalyticsMessage("172.30.182.228", "Spotify/8.6.70.1102%20Android/30%20(SM-N976V)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("172.30.182.228", "Spotify/8.6.70.1102%20Android/30%20(SM-N976V)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("172.28.93.52", "AppleCoreMedia/1.0.0.18F72%20(iPhone;%20U;%20CPU%20OS%2014_6%20like%20Mac%20OS%20X;%20en_us)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("172.28.93.52", "AppleCoreMedia/1.0.0.18F72%20(iPhone;%20U;%20CPU%20OS%2014_6%20like%20Mac%20OS%20X;%20en_us)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("7b3d:3527:e227:7eda:c2b4:2097:769c:80dc", "Spotify/8.6.68%20iOS/15.0.1%20(iPhone12,5)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("172.16.60.182", "Mozilla/5.0%20(X11;%20Linux%20x86_64)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Chrome/94.0.4606.81%20Safari/537.36", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
expected.add(new AnalyticsMessage("172.30.66.165", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0", "610b5c3ec9e77c0008e35c5f", "La Florida y la pandemia. Bolsonaro y el Tribunal Electoral. Colombia, el fútbol y la violencia"));
expected.add(new AnalyticsMessage("172.30.66.165", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0", "60cab0b44cedfd0008158c69", "Hoy, episodio especial. Los 50 años de la Guerra contra las Drogas. La cumbre Biden-Putin"));
expected.add(new AnalyticsMessage("172.22.66.223", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0", "60cbb6c646e0fb000846fa8b", "Washington’s revolving door hits Biden on the way in"));
expected.add(new AnalyticsMessage("172.29.183.103", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0", "6143d59846e0fb0008fb52e5", "America’s Song, Part 2"));
    return expected;
  }

}
