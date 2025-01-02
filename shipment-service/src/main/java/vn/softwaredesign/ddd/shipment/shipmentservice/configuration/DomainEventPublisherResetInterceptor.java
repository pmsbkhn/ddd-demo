package vn.softwaredesign.ddd.shipment.shipmentservice.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;


@Component
public class DomainEventPublisherResetInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Reset DomainEventPublisher trước khi xử lý request
        DomainEventPublisher.instance().reset();

        return true;
    }
}
