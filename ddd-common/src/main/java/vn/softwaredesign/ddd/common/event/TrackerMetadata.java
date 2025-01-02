package vn.softwaredesign.ddd.common.event;

import java.time.LocalDateTime;

public class TrackerMetadata {
    private String purpose;    // replay, real-time, batch
    private String scope;      // user-events, order-events
    private LocalDateTime createdAt; // Thời điểm khởi tạo
    private String status;     // active, paused, completed
    private LocalDateTime updatedAt;

    public TrackerMetadata(String purpose,
                           String scope,
                           LocalDateTime createdAt,
                           String status,
                           LocalDateTime updatedAt) {
        this.purpose = purpose;
        this.scope = scope;
        this.createdAt = createdAt;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public String purpose() {
        return purpose;
    }

    public String scope() {
        return scope;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public String status() {
        return status;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
