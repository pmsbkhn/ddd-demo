package vn.softwaredesign.ddd.event.springmechanism.persistence;


import jakarta.persistence.*;
import vn.softwaredesign.ddd.common.event.PublishedMessageTracker;
import vn.softwaredesign.ddd.common.event.TrackerId;
import vn.softwaredesign.ddd.common.event.TrackerMetadata;

import java.time.LocalDateTime;

@Entity
@Table(name = "published_message_trackers")
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

	public Long getTrackerId() {
		return trackerId;
	}

	public void setTrackerId(Long trackerId) {
		this.trackerId = trackerId;
	}

	public long getMostRecentPublishedMessageId() {
		return mostRecentPublishedMessageId;
	}

	public void setMostRecentPublishedMessageId(long mostRecentPublishedMessageId) {
		this.mostRecentPublishedMessageId = mostRecentPublishedMessageId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
   
}
