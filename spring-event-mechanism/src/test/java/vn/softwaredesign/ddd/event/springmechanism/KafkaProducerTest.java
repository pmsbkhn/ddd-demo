package vn.softwaredesign.ddd.event.springmechanism;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import vn.softwaredesign.ddd.common.event.Destination;
import vn.softwaredesign.ddd.common.event.MessageParameters;
import vn.softwaredesign.ddd.common.event.Notification;
import vn.softwaredesign.ddd.event.springmechanism.messaging.KafkaJsonMessageProducer;
import vn.softwaredesign.ddd.shipment.domain.model.DriverID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentAssigned;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableKafka
@EmbeddedKafka(
        partitions = 1,
        topics = {"test-topic"},
        controlledShutdown = true,
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092",
                "auto.create.topics.enable=true"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class KafkaProducerTest {

    @Autowired
    private KafkaTemplate<String, String> jsonKafkaTemplate;

    @Autowired
    private KafkaJsonMessageProducer jsonMessageProducer;

    @Autowired
    private ApplicationContext context;

    private final BlockingQueue<ConsumerRecord<String, String>> records = new LinkedBlockingQueue<>();

    @Test
    void testKafkaJsonProducer_SendNotification_Success() throws InterruptedException {
        // Given
        Notification notification = new Notification();
        notification.setNotificationId(1001L);
        notification.setTypeName("UserCreated");
        notification.setOccurredOn(LocalDateTime.now());
        ShipmentID shipmentID = new ShipmentID("SHIPMENT123");
        DriverID driverID = new DriverID("DRIVER456");
        LocalDateTime now = LocalDateTime.now();

        ShipmentAssigned event = new ShipmentAssigned(shipmentID, driverID, now);
        notification.setDomainEvent(event);

        Destination destination = new Destination(
                "test-topic",
                "standard",
                "user.created",
                false,
                "JSON"
        );

        MessageParameters parameters = new MessageParameters.Builder()
                .destination(destination)
                .addHeader("eventType", notification.type())
                .addHeader("notificationId", String.valueOf(notification.notificationId()))
                .addHeader("timestamp", notification.occurredOn().toString())
                .format("JSON")
                .build();

        // Start Kafka Listener
        startKafkaListener();

        // When
        jsonMessageProducer.send(notification, parameters);

        // Then
        ConsumerRecord<String, String> received = records.poll(10, TimeUnit.SECONDS);
        assertThat(received).isNotNull();
        assertThat(received.topic()).isEqualTo("test-topic");
        assertThat(received.key()).isEqualTo("user.created");
        assertThat(received.value()).contains("\"typeName\":\"UserCreated\"");
        assertThat(received.value()).contains("\"notificationId\":1001");
    }

    private void startKafkaListener() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);

        ContainerProperties containerProps = new ContainerProperties("test-topic");
        containerProps.setMessageListener((MessageListener<String, String>) records::add);

        KafkaMessageListenerContainer<String, String> container =
                new KafkaMessageListenerContainer<>(consumerFactory, containerProps);

        container.start();
        ContainerTestUtils.waitForAssignment(container, 1);
    }

}
