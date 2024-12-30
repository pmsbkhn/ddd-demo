package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.common.domain.model.Identity;

import java.util.Objects;

public class DriverID implements Identity<String> {
    private final String value;

    public DriverID(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DriverID driverID = (DriverID) o;
        return Objects.equals(value, driverID.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String id() {
        return this.value;
    }
}
