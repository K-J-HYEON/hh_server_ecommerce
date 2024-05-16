package hhplus.ecommerce.domain.payment.event;

import hhplus.ecommerce.config.DataPlatformSendService;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.product.StockService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentEventListener {

    private final OrderService orderService;

    private final StockService stockService;

    private final CartService cartService;

    private final DataPlatformSendService sendService;

    public PaymentEventListener(OrderService orderService, StockService stockService, CartService cartService, DataPlatformSendService sendService) {
        this.orderService = orderService;
        this.stockService = stockService;
        this.cartService = cartService;
        this.sendService = sendService;
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentSuccessHandler(PaymentSuccessEvent event) {
        sendService.send(event.order(), event.payment());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentFailHandler(PaymentFailEvent event) {
        orderService.orderFailed(event.order());
        stockService.compensationOrderStock(event.order());
        cartService.compensateCartItems(event.order());
    }
}