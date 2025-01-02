package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;

import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;

import java.util.Set;


@Component
public class DriverOa implements DriverRepository {
    @Override
    public Set<Driver> selectWhereWithinDistance(double radius) {
        // TODO
        return Set.of();
    }
}
