package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.domain.product.StockService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OrderEventListener {

    private final StockService stockService;

    public OrderEventListener(StockService stockService) {
        this.stockService = stockService;
    }


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void orderCreatedHandler(OrderCreatedEvent event) {
        stockService.updateStockCountForOrder(event.order());
    }
}