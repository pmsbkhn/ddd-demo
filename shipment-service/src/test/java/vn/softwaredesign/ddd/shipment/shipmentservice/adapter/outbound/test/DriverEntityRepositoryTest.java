package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverEntityRepositoryTest extends JpaRepository<DriverEntityTest, String>, JpaSpecificationExecutor<DriverEntityTest>{
//	@Query("""
//			    SELECT d
//			    FROM DriverEntityTest d
//			    WHERE (6371 * acos(cos(radians(:centerLatitude))
//			             * cos(radians(d.currentLocation.latitude))
//			             * cos(radians(d.currentLocation.longitude) - radians(:centerLongitude))
//			             + sin(radians(:centerLatitude))
//			             * sin(radians(d.currentLocation.latitude)))) <= :radius
//			""")
//	List<DriverEntityTest> findDriver(@Param("centerLatitude") double centerLatitude,
//			@Param("centerLongitude") double centerLongitude, @Param("radius") double radius);
}
