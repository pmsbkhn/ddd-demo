package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverStatus;

import java.util.Set;
import java.util.stream.Collectors;

public class DriverStatusSpecification {

    private final DriverStatus requiredStatus;

    public DriverStatusSpecification(DriverStatus requiredStatus) {
        this.requiredStatus = requiredStatus;
    }

    public Set<Driver> matches(Set<Driver> candidates) {
        return candidates.stream()
                .filter(candidate -> candidate.inStatus(requiredStatus))
                .collect(Collectors.toSet());
    }
}
