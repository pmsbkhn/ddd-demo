package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.serializer.AbstractSerializer;

public class NotificationSerializer extends AbstractSerializer {

    private static NotificationSerializer notificationSerializer;

    public static synchronized NotificationSerializer instance() {
        if (NotificationSerializer.notificationSerializer == null) {
            NotificationSerializer.notificationSerializer = new NotificationSerializer();
        }

        return NotificationSerializer.notificationSerializer;
    }

    protected NotificationSerializer(boolean isCompact) {
        super(isCompact);
    }

    protected NotificationSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public String serialize(Notification aNotification) {
        return this.gson().toJson(aNotification);
    }

    public <T extends Notification> T deserialize(String aSerialization, final Class<T> aType) {
        return this.gson().fromJson(aSerialization, aType);
    }

    private NotificationSerializer() {
        this(false, false);
    }
}
