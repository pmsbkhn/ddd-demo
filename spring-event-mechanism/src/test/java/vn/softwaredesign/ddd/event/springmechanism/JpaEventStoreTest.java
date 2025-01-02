package vn.softwaredesign.ddd.event.springmechanism;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.event.StoredEvent;
import vn.softwaredesign.ddd.common.lang.SnowflakeIdGenerator;
import vn.softwaredesign.ddd.event.springmechanism.persistence.JpaEventStore;
import vn.softwaredesign.ddd.shipment.domain.model.DriverID;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentAssigned;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentID;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@EntityScan(basePackages = {
        "vn.softwaredesign.ddd.event.springmechanism.persistence"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:event_store;DB_CLOSE_DELAY=-1;MODE=MYSQL",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.hibernate.ddl-auto=none",
        "spring.sql.init.mode=always",
        "spring.sql.init.schema-locations=classpath:ddls.sql"
})
public class JpaEventStoreTest {
    @Autowired
    private EntityManager entityManager;

    private JpaEventStore eventStore;

    @BeforeEach
    void setUp() {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1L, 1L);
        entityManager.createNativeQuery("DROP TABLE IF EXISTS stored_events").executeUpdate();
        entityManager.createNativeQuery(
                "CREATE TABLE stored_events (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "event_id BIGINT NOT NULL UNIQUE, " +
                        "event_type VARCHAR(255) NOT NULL, " +
                        "event_body TEXT NOT NULL, " +
                        "occurred_on DATETIME NOT NULL" +
                        ")"
        ).executeUpdate();
        eventStore = new JpaEventStore(entityManager, idGenerator);
    }

    @Test
    @DisplayName("Append event successfully")
    void appendEvent() {
        // Given
        ShipmentID shipmentID = new ShipmentID("SHIPMENT123");
        DriverID driverID = new DriverID("DRIVER456");
        LocalDateTime now = LocalDateTime.now();

        ShipmentAssigned event = new ShipmentAssigned(shipmentID, driverID, now);

        // When
        StoredEvent storedEvent = eventStore.append(event);

        // Then
        assertThat(storedEvent).isNotNull();
        assertThat(storedEvent.typeName()).isEqualTo(ShipmentAssigned.class.getName());
        assertThat(storedEvent.eventBody()).contains("SHIPMENT123");
        assertThat(storedEvent.eventBody()).contains("DRIVER456");
    }

    @Test
    @DisplayName("Retrieve events between IDs")
    void getAllStoredEventsBetween() {
        // Given
        eventStore.append(new ShipmentAssigned(
                new ShipmentID("SHIPMENT001"),
                new DriverID("DRIVER001"),
                LocalDateTime.now())
        );
        eventStore.append(new ShipmentAssigned(
                new ShipmentID("SHIPMENT002"),
                new DriverID("DRIVER002"),
                LocalDateTime.now())
        );

        // When
        List<StoredEvent> events = eventStore.allStoredEventsBetween(1, 2);

        // Then
        assertThat(events).hasSize(2);
        assertThat(events.get(0).typeName()).isEqualTo(ShipmentAssigned.class.getName());

    }
//
//    @Test
//    @DisplayName("Retrieve events since a specific ID")
//    void getAllStoredEventsSince() {
//        // Given
//        eventStore.append(new TestDomainEvent());
//        eventStore.append(new TestDomainEvent());
//        eventStore.append(new TestDomainEvent());
//
//        // When
//        List<StoredEvent> events = eventStore.allStoredEventsSince(1);
//
//        // Then
//        assertThat(events).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("Count stored events")
//    void countStoredEvents() {
//        // Given
//        eventStore.append(new TestDomainEvent());
//        eventStore.append(new TestDomainEvent());
//
//        // When
//        long count = eventStore.countStoredEvents();
//
//        // Then
//        assertThat(count).isEqualTo(2);
//    }

    @AfterEach
    void tearDown() {
        entityManager.createQuery("DELETE FROM StoredEventEntity").executeUpdate();
    }

}
