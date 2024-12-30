package vn.softwaredesign.ddd.shipment.domain.model;

import vn.softwaredesign.ddd.common.domain.model.Identity;
import vn.softwaredesign.ddd.common.lang.StringUtils;

import java.util.Objects;

public class ShipmentID implements Identity<String> {
    private final String value;

    public ShipmentID(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("Shipment ID cannot be empty");
        }
        this.value = value;
    }

    @Override
    public String id() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShipmentID that = (ShipmentID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
