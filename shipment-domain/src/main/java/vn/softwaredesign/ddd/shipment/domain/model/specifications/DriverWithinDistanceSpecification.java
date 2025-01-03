package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;

import java.util.Set;

public class DriverWithinDistanceSpecification {

    private final double radiusInKm; // unit: km
    private final Location centerLocation;

    public DriverWithinDistanceSpecification(Location centerPoint, double radiusInKm) {
    	this.centerLocation = centerPoint;
        this.radiusInKm = radiusInKm;
    }

    public Set<Driver> matches(DriverRepository aDriverRepository) {
        return aDriverRepository.selectWhereWithinDistance(centerLocation, radiusInKm);
    }
}
