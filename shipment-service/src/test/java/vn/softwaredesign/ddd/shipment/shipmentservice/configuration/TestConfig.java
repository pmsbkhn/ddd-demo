package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vn.softwaredesign.ddd.common.event.EventStore;
import vn.softwaredesign.ddd.common.lang.SnowflakeIdGenerator;
import vn.softwaredesign.ddd.event.springmechanism.persistence.JpaEventStore;
import vn.softwaredesign.ddd.shipment.application.ShipmentEventSubscriberProcessor;
import vn.softwaredesign.ddd.shipment.application.inbound.ShipmentAssignment;
import vn.softwaredesign.ddd.shipment.application.usecases.ShipmentAssignmentUseCase;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
import vn.softwaredesign.ddd.shipment.domain.model.FindDriverByBestRouteService;
import vn.softwaredesign.ddd.shipment.domain.model.FindDriverForShipmentService;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test.*;

import javax.sql.DataSource;


@Profile("test")
@Configuration
@EntityScan(basePackages = {
        "vn.softwaredesign.ddd.shipment.shipmentservice",
        "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test",
        "vn.softwaredesign.ddd.event.springmechanism.persistence"
})
@EnableJpaRepositories(basePackages = {
        "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test"
})
@ComponentScan(basePackages = {"vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test"})
@EnableTransactionManagement
public class TestConfig {
	
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("");
	    return dataSource;
	}

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource h2DataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(h2DataSource);
        factoryBean.setPackagesToScan("vn.softwaredesign.ddd.event.springmechanism.persistence",
                "vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test",
                "vn.softwaredesign.ddd.shipment.shipmentservice");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factoryBean;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public SnowflakeIdGenerator idGenerator() {
        return new SnowflakeIdGenerator(1L, 1L);
    }

    @Bean
    public EventStore eventStore(EntityManager entityManager, SnowflakeIdGenerator idGenerator) {
        return new JpaEventStore(entityManager, idGenerator);
    }

    @Bean
    public ShipmentEventSubscriberProcessor shipmentEventSubscriberProcessor(EventStore eventStore) {
        return new ShipmentEventSubscriberProcessor(eventStore);
    }
    
//    @Bean
//    public DriverRepository driverRepository(DriverEntityRepositoryTest driverEntityRepositoryTest) {
//    	return new DriverOaTest(driverEntityRepositoryTest);
//    }

    @Bean
    public ShipmentRepository shipmentRepository(ShipmentEntityRepositoryTest shipmentEntityRepository) {
        return new ShipmentOaTest(shipmentEntityRepository);
    }

    @Bean
    public FindDriverForShipmentService findDriverForShipmentService(DriverRepository driverRepository) {
        return new FindDriverByBestRouteService(driverRepository);
    }

    @Bean
    public ShipmentAssignment shipmentAssignmentUseCase(ShipmentRepository shipmentRepository,
                                                        FindDriverForShipmentService findDriverForShipmentService,
                                                        EventStore eventStore) {
        return new ShipmentAssignmentUseCase(shipmentRepository, findDriverForShipmentService, eventStore);
    }
    
//    @Bean
//    public ResetService resetService(EntityManager entityManager) {
//    	return new ResetService(entityManager);
//    }

//    @Bean
//    public ShipmentService shipmentService(ShipmentAssignment shipmentAssignment) {
//        return new ShipmentService(shipmentAssignment);
//    }
}
