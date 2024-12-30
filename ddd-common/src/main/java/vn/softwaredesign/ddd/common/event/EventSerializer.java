package vn.softwaredesign.ddd.common.event;

import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.serializer.AbstractSerializer;

public class EventSerializer extends AbstractSerializer {

    private static EventSerializer eventSerializer;

    public static synchronized EventSerializer instance() {
        if (EventSerializer.eventSerializer == null) {
            EventSerializer.eventSerializer = new EventSerializer();
        }

        return EventSerializer.eventSerializer;
    }

    public String serialize(DomainEvent aDomainEvent) {
        return this.gson().toJson(aDomainEvent);
    }

    public <T extends DomainEvent> T deserialize(String aSerialization, final Class<T> aType) {
        return this.gson().fromJson(aSerialization, aType);
    }


    protected EventSerializer(boolean isCompact) {
        super(isCompact);
    }

    protected EventSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    private EventSerializer() {
        this(false, false);
    }
}
