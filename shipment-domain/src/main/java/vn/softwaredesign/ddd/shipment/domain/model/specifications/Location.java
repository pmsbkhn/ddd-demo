package vn.softwaredesign.ddd.shipment.domain.model.specifications;

public class Location {
    private String name;
    private double latitude;
    private double longitude;
    private LocationType type;
    private TimeWindow expectedTime;
    private TimeWindow actualTime;
}
