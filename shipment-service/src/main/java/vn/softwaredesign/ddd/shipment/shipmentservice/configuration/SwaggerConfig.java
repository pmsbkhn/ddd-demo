package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI shipmentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Shipment Service API")
                        .version("1.0")
                        .description("API Documentation for Shipment Service")
                );
    }
}
