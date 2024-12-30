package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;

import java.util.Set;

public class DriverWithinDistanceSpecification {

    private final double radiusInKm; // unit: km

    public DriverWithinDistanceSpecification(double radiusInKm) {
        this.radiusInKm = radiusInKm;
    }

    public Set<Driver> matches(DriverRepository aDriverRepository) {
        return aDriverRepository.selectWhereWithinDistance(radiusInKm);
    }
}
