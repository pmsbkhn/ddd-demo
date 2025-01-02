package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.common.event.EventSubscriberConfig;
import vn.softwaredesign.ddd.shipment.application.configuration.ShipmentEventSubscriberConfig;

@Configuration
public class ShipmentEventSubscriberSpringConfig {
    @Bean
    public EventSubscriberConfig eventSubscriberConfig(EventStore eventStore) {
        ShipmentEventSubscriberConfig shipmentEventSubscriberConfig = new ShipmentEventSubscriberConfig(eventStore);
        shipmentEventSubscriberConfig.registerAllSubscribers();

        return shipmentEventSubscriberConfig;
    }
}
