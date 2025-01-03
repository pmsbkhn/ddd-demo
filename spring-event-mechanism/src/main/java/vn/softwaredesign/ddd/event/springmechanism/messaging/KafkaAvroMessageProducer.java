package vn.softwaredesign.ddd.event.springmechanism.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import vn.softwaredesign.ddd.common.event.Notification;


public class KafkaAvroMessageProducer extends KafkaMessageProducer<Object> {

    public KafkaAvroMessageProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    protected Object serializeNotification(Notification aNotification) {
        return NotificationAvroMessage.fromNotification(aNotification);
    }
}
