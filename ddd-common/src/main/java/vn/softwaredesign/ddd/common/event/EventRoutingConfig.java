package vn.softwaredesign.ddd.common.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EventRoutingConfig {
    private final Map<String, Destination> eventRoutingMap = new HashMap<>();

    public EventRoutingConfig(Map<String, Map<String, String>> configMap) {
        for (Map.Entry<String, Map<String, String>> entry : configMap.entrySet()) {
            String eventType = entry.getKey();
            Map<String, String> details = entry.getValue();

            Destination destination = new Destination(
                    details.get("name"),
                    details.get("type"),
                    details.get("routingKey"),
                    Boolean.parseBoolean(details.get("durable")),
                    details.getOrDefault("format", "JSON")
            );

            eventRoutingMap.put(eventType, destination);
        }
    }

    public Optional<Destination> getDestinationForEvent(String eventType) {
        return Optional.ofNullable(eventRoutingMap.get(eventType));
    }

    public void addEventRouting(String eventType, Destination destination) {
        eventRoutingMap.put(eventType, destination);
    }

    public void removeEventRouting(String eventType) {
        eventRoutingMap.remove(eventType);
    }

    public Map<String, Destination> getAllRoutes() {
        return new HashMap<>(eventRoutingMap);
    }

    public NotificationFormat getFormatForEvent(String type) {
        return NotificationFormat.valueOf(type);
    }
}
