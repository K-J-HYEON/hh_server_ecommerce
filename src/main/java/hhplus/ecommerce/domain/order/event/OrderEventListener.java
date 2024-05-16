package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.domain.product.StockService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventListener {

    private final StockService stockService;

    public OrderEventListener(StockService stockService) {
        this.stockService = stockService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void orderCreatedHandler(OrderCreatedEvent event) {
        stockService.updateStockCountForOrder(event.order());
    }
}