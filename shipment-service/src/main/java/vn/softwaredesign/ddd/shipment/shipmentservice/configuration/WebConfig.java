package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DomainEventPublisherResetInterceptor resetInterceptor;

    public WebConfig(DomainEventPublisherResetInterceptor resetInterceptor) {
        this.resetInterceptor = resetInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resetInterceptor);
    }

}
