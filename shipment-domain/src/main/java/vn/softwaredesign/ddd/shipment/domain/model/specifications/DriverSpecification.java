package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;

public class DriverSpecification {

    private final double allowedRelativeDeviation;

    public DriverSpecification(double allowedRelativeDeviation) {
        if (allowedRelativeDeviation < 0 || allowedRelativeDeviation > 1) {
            throw new IllegalArgumentException("The allowed relative deviation must be between 0 and 1");
        }
        this.allowedRelativeDeviation = allowedRelativeDeviation;
    }

    public Boolean matches(Driver aDriver) {
        // TODO
        return null;
    }

}
