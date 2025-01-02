package vn.softwaredesign.ddd.common.domain.model;

import java.time.LocalDateTime;

public interface DomainEvent {
    int eventVersion();
    LocalDateTime occurredOn();

}
