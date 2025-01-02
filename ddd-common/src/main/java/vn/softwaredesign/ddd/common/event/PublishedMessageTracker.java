package vn.softwaredesign.ddd.common.event;

public class PublishedMessageTracker {
    public PublishedMessageTracker(TrackerId trackerId,
                                   long mostRecentPublishedMessageId,
                                   TrackerMetadata metadata) {
        this.trackerId = trackerId;
        this.mostRecentPublishedMessageId = mostRecentPublishedMessageId;
        this.metadata = metadata;
    }

    private TrackerId trackerId;
    private long mostRecentPublishedMessageId;
    private TrackerMetadata metadata;

    public Long mostRecentPublishedMessageId() {
        return mostRecentPublishedMessageId;
    }

    public void setMostRecentPublishedNotificationId(long mostRecentId) {
        this.mostRecentPublishedMessageId = mostRecentId;
    }

    public TrackerMetadata metadata() {
        return metadata;
    }

    public TrackerId trackerId() {
        return trackerId;
    }
}
