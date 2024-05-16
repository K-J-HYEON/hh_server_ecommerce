package hhplus.ecommerce.domain.product.event;

import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.payment.PaymentService;
import hhplus.ecommerce.domain.product.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class StockEventListener {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final StockService stockService;

    public StockEventListener(PaymentService paymentService, OrderService orderService, StockService stockService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.stockService = stockService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void stockDecreaseEventFailSuccess(StockDecreaseEventSuccess eventSuccess) {
        paymentService.pay(eventSuccess.order());
    }
}
