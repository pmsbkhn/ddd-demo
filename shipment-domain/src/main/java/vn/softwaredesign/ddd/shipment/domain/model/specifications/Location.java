package vn.softwaredesign.ddd.shipment.domain.model.specifications;

public class Location {
    private String name;
    private double latitude;
    private double longitude;
    private LocationType type;
    private TimeWindow expectedTime;
    private TimeWindow actualTime;
    
    public Location(String name, double latitude, double longitude) {
    	this.name = name;
    	this.latitude = latitude;
    	this.longitude = longitude;
    }
    
    public String name() {
    	return this.name;
    }
    
    public double latitude() {
    	return this.latitude;
    }
    
    public double longitude() {
    	return this.longitude;
    }
    
}
