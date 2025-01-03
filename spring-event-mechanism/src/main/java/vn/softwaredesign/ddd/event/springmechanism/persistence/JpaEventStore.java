package vn.softwaredesign.ddd.event.springmechanism.persistence;

import jakarta.persistence.EntityManager;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.event.EventSerializer;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.common.event.StoredEvent;
import vn.softwaredesign.ddd.common.lang.SnowflakeIdGenerator;

import java.util.List;
import java.util.stream.Collectors;

//@Repository
public class JpaEventStore implements EventStore {

//    @PersistenceContext
    protected final EntityManager entityManager;

    private final SnowflakeIdGenerator idGenerator;

    public JpaEventStore(EntityManager entityManager,
                         SnowflakeIdGenerator idGenerator) {
        this.entityManager = entityManager;
        this.idGenerator = idGenerator;
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        List<StoredEventEntity> storedEventEntities = entityManager.createQuery(
                        "SELECT e FROM StoredEventEntity e " +
                                "WHERE e.id > :eventId",
                        StoredEventEntity.class)
                .setParameter("eventId", aStoredEventId)
                .getResultList();

        return storedEventEntities
                .stream()
                .map(StoredEventEntity::toStoredEvent)
                .collect(Collectors.toList());
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId) {
        List<StoredEventEntity> storedEventEntities = entityManager.createQuery(
                        "SELECT e FROM StoredEventEntity e " +
                                "WHERE e.id BETWEEN :low AND :high",
                        StoredEventEntity.class)
                .setParameter("low", aLowStoredEventId)
                .setParameter("high", aHighStoredEventId)
                .getResultList();

        return storedEventEntities
                .stream()
                .map(StoredEventEntity::toStoredEvent)
                .collect(Collectors.toList());
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization = EventSerializer
                .instance()
                .serialize(aDomainEvent);

        long eventId = idGenerator.nextId();

        StoredEvent storedEvent = new StoredEvent(eventId,
                aDomainEvent.getClass().getName(),
                aDomainEvent.occurredOn(),
                eventSerialization);

        StoredEventEntity storedEventEntity = new StoredEventEntity(storedEvent);

        entityManager.persist(storedEventEntity);

        return storedEvent;
    }

    @Override
    public long countStoredEvents() {
        return entityManager.createQuery(
                        "SELECT COUNT(e) FROM StoredEventEntity e",
                        Long.class)
                .getSingleResult();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
