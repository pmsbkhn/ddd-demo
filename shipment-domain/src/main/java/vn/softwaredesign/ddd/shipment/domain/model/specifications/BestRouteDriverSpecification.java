package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.Shipment;

import java.util.Set;

public class BestRouteDriverSpecification {
    private final RouteOptimizationStrategy optimizationStrategy;

    public BestRouteDriverSpecification(RouteOptimizationStrategy optimizationStrategy) {
        this.optimizationStrategy = optimizationStrategy;
    }

    public Driver matches(Shipment shipment, Set<Driver> drivers) {
        // TODO
        return null;
    }
}
