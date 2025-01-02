package vn.softwaredesign.ddd.common.event;

public interface PublishedMessageTrackerRepository {
    void save(PublishedMessageTracker aPublishedMessageTracker);

    PublishedMessageTracker trackerOf(String purpose);
}
