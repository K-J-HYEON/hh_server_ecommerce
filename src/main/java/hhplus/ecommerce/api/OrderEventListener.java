package hhplus.ecommerce.api;

import hhplus.ecommerce.domain.order.event.OrderCreatedEvent;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
public class OrderEventListener {

    private final ProductService productService;

    public OrderEventListener(ProductService productService) {
        this.productService = productService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onProductStockChanged(OrderCreatedEvent event) {
        List<Product> products = productService.readProductsByIds(
                event.order().items().stream()
                        .map(OrderItem::productId)
                        .toList()
        );

        productService.updateStockCount(products, event.order());
    }
}
