package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.common.lang.SnowflakeIdGenerator;
import vn.softwaredesign.ddd.shipment.application.ShipmentEventSubscriberProcessor;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignment;
import vn.softwaredesign.ddd.shipment.application.usecases.ShipmentAssignmentUseCase;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
import vn.softwaredesign.ddd.shipment.domain.model.FindDriverByBestRouteService;
import vn.softwaredesign.ddd.shipment.domain.model.FindDriverForShipmentService;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.DriverOutputAdapter;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.ShipmentOutputAdapter;

@Configuration
@Profile("!test")
public class SpringConfig {

    @Bean
    public SnowflakeIdGenerator idGenerator() {
        return new SnowflakeIdGenerator(1L, 1L);
    }

    @Bean
    public EventStore eventStore(EntityManager entityManager, SnowflakeIdGenerator idGenerator) {
        return new vn.softwaredesign.ddd.event.springmechanism.persistence.JpaEventStore(entityManager, idGenerator);
    }

    @Bean
    public ShipmentEventSubscriberProcessor shipmentEventSubscriberProcessor(EventStore eventStore) {
        return new ShipmentEventSubscriberProcessor(eventStore);
    }

    @Bean
    public DriverRepository driverRepository() {
        return new DriverOutputAdapter();
    }

    @Bean
    public ShipmentRepository shipmentRepository() {
        return new ShipmentOutputAdapter();
    }

    @Bean
    public FindDriverForShipmentService findDriverForShipmentService(DriverRepository driverRepository) {
        return new FindDriverByBestRouteService(driverRepository);
    }

    @Bean
    public ShipmentAssignment shipmentAssignment(ShipmentRepository shipmentRepository,
                                                 FindDriverForShipmentService findDriverForShipmentService,
                                                 EventStore eventStore) {
        return new ShipmentAssignmentUseCase(shipmentRepository,
                findDriverForShipmentService,
                eventStore);
    }
}
