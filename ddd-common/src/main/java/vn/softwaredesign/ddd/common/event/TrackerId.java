package vn.softwaredesign.ddd.common.event;

public class TrackerId {
    private final long value;

    public TrackerId(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }
}
