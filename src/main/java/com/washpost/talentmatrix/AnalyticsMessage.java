package com.washpost.talentmatrix;

/**
 * Provided AnalyticsMessage class.
 *
 * You do not need to modify this class.
 */
public class AnalyticsMessage {
    private String ipAddress = null;
    private String userAgent = null;
    private String episodeId = null;
    private String title = null;

    public AnalyticsMessage() {
    }

    public AnalyticsMessage(String ipAddress, String userAgent, String episodeId, String title) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.episodeId = episodeId;
        this.title = title;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(AnalyticsMessage other) {
        return (
                ((this.ipAddress == null && other.ipAddress == null) ||
                        (this.ipAddress != null && other.ipAddress != null && this.ipAddress.equals(other.ipAddress))
                ) &&
                        ((this.userAgent == null && other.userAgent == null) ||
                                (this.userAgent != null && other.userAgent != null &&
                                        this.userAgent.equals(other.userAgent))
                        ) &&
                        ((this.episodeId == null && other.episodeId == null) ||
                                (this.episodeId != null && other.episodeId != null &&
                                        this.episodeId.equals(other.episodeId))
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
                                (this.userAgent != null && other.userAgent != null &&
                                        this.userAgent.equals(other.userAgent))
                        ) &&
                        ((this.episodeId == null && other.episodeId == null) ||
                                (this.episodeId != null && other.episodeId != null &&
                                        this.episodeId.equals(other.episodeId))
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
