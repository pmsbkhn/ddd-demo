package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import vn.softwaredesign.ddd.shipment.domain.model.Driver;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.Location;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class DriverOaTest implements DriverRepository {
    private final DriverEntityRepositoryTest driverEntityRepository;

    @Autowired
    public DriverOaTest(DriverEntityRepositoryTest driverEntityRepository) {
        this.driverEntityRepository = driverEntityRepository;
    }


    @Override
    public Set<Driver> selectWhereWithinDistance(Location centerPoint, double radius) {
    	Specification<DriverEntityTest> spec = DriverEntitySpecifications
    			.withinDistance(centerPoint.latitude(), centerPoint.longitude(), radius);
    	List<DriverEntityTest> driverEntityTests = driverEntityRepository.findAll(spec);
    	
    	return driverEntityTests.stream()
    			.map(DriverEntityTest::toDomain)
    			.collect(Collectors.toSet());
    }


	@Override
	public void save(Driver aDriver) {
		DriverEntityTest driverEntity = DriverEntityTest.fromDomain(aDriver);
		driverEntityRepository.save(driverEntity);
	}
}
