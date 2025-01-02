package vn.softwaredesign.ddd.shipment.shipmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "vn.softwaredesign.ddd.shipment.shipmentservice.configuration",
        "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound",
        "vn.softwaredesign.ddd.event.springmechanism.persistence"
})
public class ShipmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipmentServiceApplication.class, args);
    }

}
