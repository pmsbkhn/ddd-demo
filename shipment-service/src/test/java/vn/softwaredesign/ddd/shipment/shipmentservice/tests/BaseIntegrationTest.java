package vn.softwaredesign.ddd.shipment.shipmentservice.tests;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import vn.softwaredesign.ddd.shipment.domain.model.DriverRepository;
import vn.softwaredesign.ddd.shipment.domain.model.ShipmentRepository;
import vn.softwaredesign.ddd.shipment.shipmentservice.adapter.outbound.test.ResetService;
import vn.softwaredesign.ddd.shipment.shipmentservice.configuration.TestConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = TestConfig.class)
@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class BaseIntegrationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected ShipmentRepository shipmentRepository;

    @Autowired
    protected ResetService resetService;

    @Autowired
    protected DriverRepository driverRepository;

    @Test
    void contextLoads() {
    	System.out.println(">>> " + context);
        assertThat(context).isNotNull();
    }

    @BeforeEach
    void resetDatabase() {
        resetService.resetDatabase();
    }
}
