package hhplus.ecommerce.domain.order.event;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publicEvent(OrderCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
