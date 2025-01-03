package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverWithinDistanceSpecification;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.Location;

import java.util.Set;

public interface DriverRepository {
	
	void save(Driver aDriver);

    default Set<Driver> selectSatisfying(DriverWithinDistanceSpecification specification) {
        return specification.matches(this);
    }

    Set<Driver> selectWhereWithinDistance(Location centerLocation, double radius);

}
