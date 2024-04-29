package hhplus.ecommerce.api;


import hhplus.ecommerce.domain.order.event.OrderPaidEvent;
import hhplus.ecommerce.domain.product.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProductEventHandler {
    private final ProductService productService;

    public ProductEventHandler(ProductService productService) {
        this.productService = productService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onProductStockChanged(OrderPaidEvent orderPaidEvent) {
        productService.updateStockCount(orderPaidEvent.products(), orderPaidEvent.orderRequest().products());
    }
}
