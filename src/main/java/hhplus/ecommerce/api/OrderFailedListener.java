package hhplus.ecommerce.api;

import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.event.OrderFailedEvent;
import hhplus.ecommerce.domain.product.StockService;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderFailedListener {

    private final OrderService orderService;
    private final StockService stockService;

    public OrderFailedListener(OrderService orderService, StockService stockService) {
        this.orderService = orderService;
        this.stockService = stockService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderFailed(OrderFailedEvent orderFailedEvent) {
        orderService.updateOrderStatus(orderFailedEvent.order(), orderFailedEvent.orderStatus());
        if (orderFailedEvent.orderStatus().equals(OrderStatus.PAY_FAILED)) {
            stockService.compensateStock(orderFailedEvent.stocks(), orderFailedEvent.orderRequest());
        }
    }
}