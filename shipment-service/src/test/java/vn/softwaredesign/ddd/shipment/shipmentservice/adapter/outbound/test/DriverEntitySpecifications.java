package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import org.springframework.data.jpa.domain.Specification;

public class DriverEntitySpecifications {
	public static Specification<DriverEntityTest> withinDistance(double centerLatitude, double centerLongitude, double radius) {
        return (root, query, criteriaBuilder) -> {
            // Haversine formula for distance calculation
            return criteriaBuilder.lessThanOrEqualTo(
                criteriaBuilder.function(
                    "6371 * acos(cos(radians(:centerLatitude)) * cos(radians(root.currentLocation.latitude)) * cos(radians(root.currentLocation.longitude) - radians(:centerLongitude)) + sin(radians(:centerLatitude)) * sin(radians(root.currentLocation.latitude))",
                    Double.class
                ),
                radius
            );
        };
    }
}
