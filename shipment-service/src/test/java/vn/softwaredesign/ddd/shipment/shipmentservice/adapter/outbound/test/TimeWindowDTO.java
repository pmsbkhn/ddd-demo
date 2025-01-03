package vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test;

import jakarta.persistence.Embeddable;
import vn.softwaredesign.ddd.shipment.domain.model.specifications.TimeWindow;

import java.time.LocalTime;

@Embeddable
public class TimeWindowDTO {
    private String arrivalTime;
    private String departureTime;

    public TimeWindow toDomain() {
        TimeWindow timeWindow = new TimeWindow();
        timeWindow.setArrival(LocalTime.parse(arrivalTime));
        timeWindow.setDeparture(LocalTime.parse(departureTime));
        
        return timeWindow;
    }

    public static TimeWindowDTO fromDomain(TimeWindow timeWindow) {
        TimeWindowDTO dto = new TimeWindowDTO();
        dto.arrivalTime = timeWindow.arrival().toString();
        dto.departureTime = timeWindow.departure().toString();
        return dto;
    }
}
