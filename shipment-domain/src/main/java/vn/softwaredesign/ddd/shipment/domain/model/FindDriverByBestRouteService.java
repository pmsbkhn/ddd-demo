package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.shipment.domain.model.specifications.BestRouteDriverSpecification;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverStatusSpecification;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverWithinDistanceSpecification;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.ShortestRouteStrategy;

import java.util.Set;
import java.util.stream.Collectors;

public class FindDriverByBestRouteService implements FindDriverForShipmentService {

    private final DriverRepository driverRepository;

    public FindDriverByBestRouteService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver findDriver(Shipment shipment) {
        Set<Driver> driversWithinDistance = driverRepository.selectSatisfying(
                new DriverWithinDistanceSpecification(shipment.pickupLocation(), 3d)
        );

        Set<Driver> availableDrivers = new DriverStatusSpecification(DriverStatus.AVAILABLE)
                .matches(driversWithinDistance);

        shipment.setDriverSpecification(null);

        Set<Driver> eligibleDrivers = availableDrivers.stream()
                .filter(driver -> shipment.driverSpecification().matches(driver))
                .collect(Collectors.toSet());

        return (new BestRouteDriverSpecification(new ShortestRouteStrategy())).matches(shipment, eligibleDrivers);
    }
}
