package com.washpost.talentmatrix;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private static String LOGS_FILE_PATH = "./data/logs.tsv";

    private static List<AnalyticsMessage> receivedMessages;

    @BeforeClass
    public static void beforeClass() {
        receivedMessages = new ArrayList<>();
        Analytics analytics = new Analytics(receivedMessages);
        analytics.processLogs(LOGS_FILE_PATH);
    }

    @Test
    public void shouldRunWithoutErrors()
    {
        List<AnalyticsMessage> receivedMessages = new ArrayList<>();
        Analytics analytics = new Analytics(receivedMessages);
        analytics.processLogs(LOGS_FILE_PATH);
        assertTrue(true);
    }

    @Test
    public void test_correctNumberOfMessages() {
        // Verify we received the right number of messages
        assert receivedMessages.size() == getExpectedData().size() : String.format(
                "Wrong number of analytics events received (expected %d, received %d)", getExpectedData().size(),
                receivedMessages.size());
    }

    @Test
    public void test_extractedCorrectFieldsFromLogData() {
        assertEqualMessageListContents(getExpectedData(), receivedMessages, false);
    }

    @Test
    public void test_extractedTitleFromAPI() {
        assertEqualMessageListContents(getExpectedData(), receivedMessages, true);
    }


    private static List<AnalyticsMessage> getExpectedData() {
        List<AnalyticsMessage> expected = new ArrayList<>();
        expected.add(new AnalyticsMessage("172.30.182.228", "Spotify/8.6.70.1102%20Android/30%20(SM-N976V)",
                "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("172.30.182.228", "Spotify/8.6.70.1102%20Android/30%20(SM-N976V)",
                "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("172.28.93.52",
                "AppleCoreMedia/1.0.0.18F72%20(iPhone;%20U;%20CPU%20OS%2014_6%20like%20Mac%20OS%20X;%20en_us)",
                "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("172.28.93.52",
                "AppleCoreMedia/1.0.0.18F72%20(iPhone;%20U;%20CPU%20OS%2014_6%20like%20Mac%20OS%20X;%20en_us)",
                "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("7b3d:3527:e227:7eda:c2b4:2097:769c:80dc",
                "Spotify/8.6.68%20iOS/15.0.1%20(iPhone12,5)", "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("172.16.60.182",
                "Mozilla/5.0%20(X11;%20Linux%20x86_64)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Chrome/94.0.4606.81%20Safari/537.36",
                "6169ec274cedfd00097fcac6", "The NBA’s Kyrie problem "));
        expected.add(new AnalyticsMessage("172.30.66.165", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0",
                "610b5c3ec9e77c0008e35c5f",
                "La Florida y la pandemia. Bolsonaro y el Tribunal Electoral. Colombia, el fútbol y la violencia"));
        expected.add(new AnalyticsMessage("172.30.66.165", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0",
                "60cab0b44cedfd0008158c69",
                "Hoy, episodio especial. Los 50 años de la Guerra contra las Drogas. La cumbre Biden-Putin"));
        expected.add(new AnalyticsMessage("172.22.66.223", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0",
                "60cbb6c646e0fb000846fa8b", "Washington’s revolving door hits Biden on the way in"));
        expected.add(new AnalyticsMessage("172.29.183.103", "Podcasts/1575.2.2%20CFNetwork/1240.0.4%20Darwin/20.6.0",
                "6143d59846e0fb0008fb52e5", "America’s Song, Part 2"));
        return expected;
    }

    private static void assertEqualMessageListContents(List<AnalyticsMessage> expected, List<AnalyticsMessage> received,
                                                       boolean includeAudioApiData) {
        for (int i = 0; i < expected.size(); i++) {
            AnalyticsMessage e = expected.get(i);
            AnalyticsMessage r = received.get(i);

            boolean isEqual = includeAudioApiData ? e.equals(r) : e.equalsWithOnlyLogFields(r);

            assert isEqual : String.format("Values are not the same at index %d (expected %s, received %s)", i,
                    e.toString(), r.toString());
        }
    }

}
