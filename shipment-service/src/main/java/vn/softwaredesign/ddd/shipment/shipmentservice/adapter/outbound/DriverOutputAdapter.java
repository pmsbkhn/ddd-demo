package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound;


import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.DriverWithinDistanceSpecification;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.Location;

import java.util.Set;

//@Component
public class DriverOutputAdapter implements DriverRepository {
    @Override
    public Set<Driver> selectSatisfying(DriverWithinDistanceSpecification specification) {
        return DriverRepository.super.selectSatisfying(specification);
    }

    @Override
    public Set<Driver> selectWhereWithinDistance(Location centerPoint, double radius) {
        return Set.of();
    }

	@Override
	public void save(Driver aDriver) {
		// TODO Auto-generated method stub
		
	}
}
