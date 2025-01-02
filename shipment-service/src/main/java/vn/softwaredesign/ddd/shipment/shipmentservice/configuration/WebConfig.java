package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private DomainEventPublisherResetInterceptor resetInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resetInterceptor);
    }

    @Autowired
    public DomainEventPublisherResetInterceptor setResetInterceptor(DomainEventPublisherResetInterceptor resetInterceptor) {
        return resetInterceptor;
    }
}
