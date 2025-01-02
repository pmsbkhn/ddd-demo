//package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import vn.softwaredesign.ddd.common.event.EventStore;
//import vn.softwaredesign.ddd.common.lang.SnowflakeIdGenerator;
//import vn.softwaredesign.ddd.event.springmechanism.persistence.JpaEventStore;
//import vn.softwaredesign.ddd.shipment.application.ShipmentEventSubscriberProcessor;
//import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignment;
//import vn.softwaredesign.ddd.shipment.application.usecases.ShipmentAssignmentUseCase;
//import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
//import vn.softwaredesign.ddd.shipment.domain.model.FindDriverByBestRouteService;
//import vn.softwaredesign.ddd.shipment.domain.model.FindDriverForShipmentService;
//import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
//import vn.softwaredesign.ddd.shipment.shipmentservice.service.ShipmentService;
//
//import javax.sql.DataSource;
//
//
//@Profile("test")
//@TestConfiguration
//@EntityScan(basePackages = {
//        "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound",
//        "vn.softwaredesign.ddd.event.springmechanism.persistence"
//})
//public class TestConfig {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource h2DataSource) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(h2DataSource);
//        factoryBean.setPackagesToScan("vn.softwaredesign.ddd.event.springmechanism.persistence",
//                "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound");
//        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return factoryBean;
//    }
//
//    @Bean
//    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    @Bean
//    public SnowflakeIdGenerator idGenerator() {
//        return new SnowflakeIdGenerator(1L, 1L);
//    }
//
//    @Bean
//    public EventStore eventStore(EntityManager entityManager, SnowflakeIdGenerator idGenerator) {
//        return new JpaEventStore(entityManager, idGenerator);
//    }
//
//    @Bean
//    public ShipmentEventSubscriberProcessor shipmentEventSubscriberProcessor(EventStore eventStore) {
//        return new ShipmentEventSubscriberProcessor(eventStore);
//    }
//
//    @Bean
//    public FindDriverForShipmentService findDriverForShipmentService(DriverRepository driverRepository) {
//        return new FindDriverByBestRouteService(driverRepository);
//    }
//
//    @Bean
//    public ShipmentAssignment shipmentAssignmentUseCase(ShipmentRepository shipmentRepository,
//                                                        FindDriverForShipmentService findDriverForShipmentService,
//                                                        EventStore eventStore) {
//        return new ShipmentAssignmentUseCase(shipmentRepository, findDriverForShipmentService, eventStore);
//    }
//
//    @Bean
//    public ShipmentService shipmentService(ShipmentAssignment shipmentAssignment) {
//        return new ShipmentService(shipmentAssignment);
//    }
//}
