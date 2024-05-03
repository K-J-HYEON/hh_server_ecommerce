package hhplus.ecommerce.application;

import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.common.LockHandler;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.event.OrderCreatedEvent;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentService;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
import hhplus.ecommerce.domain.product.StockService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class OrderUseCase {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final LockHandler lockHandler;
    private final StockService stockService;
    private final PaymentService paymentService;
    private static final String ORDER_LOCK_PREFIX = "ORDER_";

    public OrderUseCase(UserService userService,
                        ProductService productService,
                        OrderService orderService,
                        ApplicationEventPublisher applicationEventPublisher, LockHandler handler, LockHandler lockHandler, StockService stockService, PaymentService paymentService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.lockHandler = lockHandler;
        this.stockService = stockService;
        this.paymentService = paymentService;
    }

    @Transactional
    public OrderPaidResult order(Long userId, OrderRequest req) {
        User user = userService.getUser(userId);
        List<Product> products = productService.readProductsByIds(req.products().stream()
                .map(OrderRequest.ProductOrderRequest::id)
                .toList());

        String key = createLockKey(products);

        lockHandler.lock(key, 2, 1);

        try {
            stockService.decreaseProductStock(products, req);
        } finally {
            lockHandler.unlock(key);
        }

        Order order = orderService.order(user, products, req);
        Payment payment = paymentService.pay(user, order, req);

        applicationEventPublisher.publishEvent(new OrderCreatedEvent(products, req.products(), order, payment));
        return OrderPaidResult.from(order);
    }

    private String createLockKey(List<Product> products) {
        StringBuilder key = new StringBuilder(ORDER_LOCK_PREFIX);
        for (Product product : products) {
            key.append(product.id());
        }
        return key.toString();
    }
}