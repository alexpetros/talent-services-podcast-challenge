package com.washpost.talentmatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

public class Analytics {
    // Information about a podcast can be fetched at WAPO_AUDIO_API_BASE_URL + episodeId
    private static String LOGS_FILE_PATH = "./data/logs.tsv";
    private static String WAPO_AUDIO_API_BASE_URL = "https://d1sk0wal7twks.cloudfront.net/api/v1/audio/";

    private List<AnalyticsMessage> messageList;
    private HttpClient httpClient;

    public Analytics(List<AnalyticsMessage> messageList) {
        this.httpClient = HttpClient.newHttpClient();
        this.messageList = messageList;
    }

    private void postAnalytics(AnalyticsMessage message) {
        this.messageList.add(message);
    }

    public void processLogs(String filepath) {

        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = TSVReader.readLine()) != null) {
                // TODO: Add each line to structured data
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // TODO: Filter the data based on the given criteria and push to the Analytics API
        // To push an AnalyticsMessage object to the mocked Analytics API, use:
        // this.postAnalytics(message);
    }

    /**
     * Test code below
     *
     * PLEASE DO NOT MODIFY
     */
    public static void main(String[] args) {
        System.out.println("Ready to begin!");

        List<AnalyticsMessage> messageList = new ArrayList<>();
        Analytics analytics = new Analytics(messageList);

        analytics.processLogs(LOGS_FILE_PATH);
        System.out.println("✅ provided code ran without errors");
    }

}
