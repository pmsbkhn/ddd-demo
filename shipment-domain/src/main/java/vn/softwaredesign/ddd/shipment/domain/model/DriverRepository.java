package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverWithinDistanceSpecification;

import java.util.Set;

public interface DriverRepository {

    default Set<Driver> selectSatisfying(DriverWithinDistanceSpecification specification) {
        return specification.matches(this);
    }

    Set<Driver> selectWhereWithinDistance(double radius);

}
