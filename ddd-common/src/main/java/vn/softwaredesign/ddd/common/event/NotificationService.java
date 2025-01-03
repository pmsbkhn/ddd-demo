package vn.softwaredesign.ddd.common.event;

import java.util.List;

public class NotificationService<T> {

    private final EventStore eventStore;;
    private final PublishedMessageTrackerRepository publishedMessageTrackerRepository;
    private final String purpose;
    private final MessageProducer<String> stringMessageProducer;
    private final MessageProducer<Object> avroMessageProducer;
    private final EventRoutingConfig eventRoutingConfig;


    public NotificationService(EventStore eventStore,
                               PublishedMessageTrackerRepository publishedMessageTrackerRepository,
                               String purpose,
                               MessageProducer<String> stringMessageProducer,
                               MessageProducer<Object> avroMessageProducer,
                               EventRoutingConfig eventRoutingConfig) {
        this.eventStore = eventStore;
        this.publishedMessageTrackerRepository = publishedMessageTrackerRepository;
        this.purpose = purpose;
        this.stringMessageProducer = stringMessageProducer;
        this.avroMessageProducer = avroMessageProducer;
        this.eventRoutingConfig = eventRoutingConfig;
    }

    public void publishNotifications() {
        PublishedMessageTracker publishedMessageTracker = this.publishedMessageTrackerRepository.trackerOf(purpose);

        if (publishedMessageTracker == null) {
            throw new IllegalArgumentException("No tracker found for purpose: " + purpose);
        }

        List<Notification> notifications = this.listUnpublishedNotifications(publishedMessageTracker.mostRecentPublishedMessageId());

        for (Notification notification : notifications) {
            this.publish(notification);
        }

        this.trackMostRecentPublishedMessage(publishedMessageTracker, notifications);
    }

    private void trackMostRecentPublishedMessage(PublishedMessageTracker aPublishedMessageTracker,
                                                 List<Notification> aNotifications) {
        int lastIndex = aNotifications.size() - 1;

        if (lastIndex >= 0) {
            long mostRecentId = aNotifications.get(lastIndex).notificationId();
            aPublishedMessageTracker.setMostRecentPublishedNotificationId(mostRecentId);
            publishedMessageTrackerRepository.save(aPublishedMessageTracker);
        }
    }

    private void publish(Notification aNotification) {
        MessageProducer<?> messageProducer = this.chooseMessageProducer(aNotification);
        MessageParameters messageParameters = this.createMessageParameters(aNotification);

        messageProducer.send(aNotification, messageParameters);
    }

    private MessageProducer<?> chooseMessageProducer(Notification aNotification) {
        NotificationFormat format = eventRoutingConfig.getFormatForEvent(aNotification.type());
        if (format == NotificationFormat.AVRO) {
            return this.avroMessageProducer;
        } else {
            return this.stringMessageProducer;
        }
    }

    private MessageParameters createMessageParameters(Notification aNotification) {
        Destination destination = eventRoutingConfig.getDestinationForEvent(aNotification.type())
                .orElseThrow(() -> new IllegalArgumentException("No routing found for event type: " + aNotification.type()));

        return new MessageParameters.Builder()
                .destination(destination)
                .addHeader("eventType", aNotification.type())
                .addHeader("notificationId", String.valueOf(aNotification.notificationId()))
                .addHeader("timestamp", aNotification.occurredOn().toString())
                .format(destination.format())
                .build();
    }

    @SuppressWarnings("unused")
	private static NotificationSerializer objectSerializer() {
        return NotificationSerializer.instance();
    }

    private List<Notification> listUnpublishedNotifications(Long aMostRecentPublishedMessageId) {
        List<StoredEvent> storedEvents = eventStore.allStoredEventsSince(aMostRecentPublishedMessageId);

        return this.notificationsFrom(storedEvents);
    }

    private List<Notification> notificationsFrom(List<StoredEvent> storedEvents) {
        // TODO
        return null;
    }

}
