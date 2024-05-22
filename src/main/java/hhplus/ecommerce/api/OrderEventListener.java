//package hhplus.ecommerce.api;
//
//import hhplus.ecommerce.domain.order.event.OrderCreatedEvent;
//import hhplus.ecommerce.domain.orderitem.OrderItem;
//import hhplus.ecommerce.domain.product.Product;
//import hhplus.ecommerce.domain.product.ProductService;
//import hhplus.ecommerce.domain.product.StockService;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;
//import java.util.List;
//
//@Component
//public class OrderEventListener {
//
//    private final ProductService productService;
//
//    private final StockService stockService;
//
//    public OrderEventListener(ProductService productService, StockService stockService) {
//        this.productService = productService;
//        this.stockService = stockService;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void onProductStockChanged(OrderCreatedEvent event) {
//        List<Product> products = productService.readProductsByIds(
//                event.order().items().stream()
//                        .map(OrderItem::productId)
//                        .toList()
//        );
//
//        productService.updateStockCount(products, event.order());
//    }
//
//
////    public void orderCreatedHandler(OrderCreatedEvent event) {
////        stockService.
////    }
//}
