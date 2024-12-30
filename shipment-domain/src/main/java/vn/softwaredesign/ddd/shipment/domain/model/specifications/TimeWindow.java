package vn.softwaredesign.ddd.shipment.domain.model.specifications;

import java.time.LocalTime;
import java.util.Objects;

public class TimeWindow {
    private LocalTime arrival;
    private LocalTime departure;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeWindow that = (TimeWindow) o;
        return Objects.equals(arrival, that.arrival) && Objects.equals(departure, that.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrival, departure);
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public LocalTime arrival() {
        return arrival;
    }

    public LocalTime departure() {
        return departure;
    }
}
