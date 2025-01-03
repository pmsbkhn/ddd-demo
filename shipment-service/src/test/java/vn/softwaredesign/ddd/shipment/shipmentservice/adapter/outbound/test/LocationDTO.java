package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.Location;

@Embeddable
@Setter
public class LocationDTO {
	@Column(name = "location_name")
	private String name;
	private double latitude;
	private double longitude;
	
	public Location toDomain() {
		return new Location(name, latitude, longitude);
	}
	
	public static LocationDTO fromDomain(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.name = location.name();
        dto.latitude = location.latitude();
        dto.longitude = location.longitude();
        
        return dto;
    }
	
	public String name() {
		return name;
	}
	
	public double latitude() {
		return latitude;
	}
	
	public double longitude() {
		return longitude;
	}
	
}
