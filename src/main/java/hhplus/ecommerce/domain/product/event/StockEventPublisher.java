package hhplus.ecommerce.domain.product.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StockEventPublisher {

    public final ApplicationEventPublisher applicationEventPublisher;

    public StockEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void success(StockDecreaseEventSuccess event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void fail(StockDecreaseEventFail event) {
        applicationEventPublisher.publishEvent(event);
    }
}