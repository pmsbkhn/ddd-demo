package vn.softwaredesign.ddd.event.springmechanism.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishedMessageTrackerRepository extends JpaRepository<PublishedMessageTrackerEntity, Long> {
    PublishedMessageTrackerEntity findByPurposeAndScope(String purpose, String scope);
}
