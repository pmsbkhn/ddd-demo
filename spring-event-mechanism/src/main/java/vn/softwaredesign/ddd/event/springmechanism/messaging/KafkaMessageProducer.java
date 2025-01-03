package vn.softwaredesign.ddd.event.springmechanism.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import vn.softwaredesign.ddd.common.event.MessageParameters;
import vn.softwaredesign.ddd.common.event.MessageProducer;
import vn.softwaredesign.ddd.common.event.Notification;

import java.util.concurrent.CompletableFuture;


public abstract class KafkaMessageProducer<T> implements MessageProducer<Notification> {

    protected final KafkaTemplate<String, T> kafkaTemplate;
    
    public KafkaMessageProducer(KafkaTemplate<String, T> kafkaTemplate) {
    	this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(Notification aNotification, MessageParameters aMessageParameters) {
        String topic = aMessageParameters.getDestination().name();
        String key = aMessageParameters.getDestination().routingKey();

        try {
            T message = serializeNotification(aNotification);
            CompletableFuture<SendResult<String, T>> future = kafkaTemplate.send(topic, key, message);
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    System.err.printf(
                            "Failed to send Kafka message - Topic: %s, Key: %s, Error: %s%n",
                            topic, key, ex.getMessage()
                    );
                } else {
                    System.out.printf(
                            "Sent Kafka message - Topic: %s, Partition: %d, Offset: %d%n",
                            result.getRecordMetadata().topic(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset()
                    );
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Kafka message", e);
        }
    }

    @Override
    public void close() {
        System.out.println("KafkaTemplate is managed by Spring, no need to close explicitly.");
    }

    protected abstract T serializeNotification(Notification aNotification);
}
