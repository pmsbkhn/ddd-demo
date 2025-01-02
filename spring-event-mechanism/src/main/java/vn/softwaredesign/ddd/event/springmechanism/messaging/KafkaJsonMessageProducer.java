package vn.softwaredesign.ddd.event.springmechanism.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.common.event.Notification;
import vn.softwaredesign.ddd.common.event.NotificationSerializer;


@Component
public class KafkaJsonMessageProducer extends KafkaMessageProducer<String> {

//    private final ObjectMapper objectMapper;

    public KafkaJsonMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
//        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected String serializeNotification(Notification aNotification) {
        try {
            return NotificationSerializer.instance().serialize(aNotification);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize Notification to JSON", e);
        }
    }
}
