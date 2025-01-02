package vn.softwaredesign.ddd.event.springmechanism.persistence;


import jakarta.persistence.*;
import lombok.Data;
import vn.softwaredesign.ddd.common.event.PublishedMessageTracker;
import vn.softwaredesign.ddd.common.event.TrackerId;
import vn.softwaredesign.ddd.common.event.TrackerMetadata;

import java.time.LocalDateTime;

@Entity
@Table(name = "published_message_trackers")
@Data
public class PublishedMessageTrackerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracker_id")
    private Long trackerId;

    @Column(name = "most_recent_published_message_id", nullable = false)
    private long mostRecentPublishedMessageId;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "scope", nullable = false)
    private String scope;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public PublishedMessageTracker toDomain() {
        TrackerMetadata metadata = new TrackerMetadata(
                this.getPurpose(),
                this.getScope(),
                this.getCreatedAt(),
                this.getStatus(),
                this.getUpdatedAt()
        );

        return new PublishedMessageTracker(
                new TrackerId(this.getTrackerId()),
                this.getMostRecentPublishedMessageId(),
                metadata
        );
    }

    public static PublishedMessageTrackerEntity toEntity(PublishedMessageTracker tracker) {
        PublishedMessageTrackerEntity entity =  new PublishedMessageTrackerEntity();
        entity.setTrackerId(tracker.trackerId().value());
        entity.setMostRecentPublishedMessageId(tracker.mostRecentPublishedMessageId());
        entity.setPurpose(tracker.metadata().purpose());
        entity.setScope(tracker.metadata().scope());
        entity.setCreatedAt(tracker.metadata().createdAt());
        entity.setStatus(tracker.metadata().status());
        entity.setUpdatedAt(tracker.metadata().updatedAt());

        return entity;
    }
}
