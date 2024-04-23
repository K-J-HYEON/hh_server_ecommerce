package hhplus.ecommerce.application;

import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.event.OrderCreatedEvent;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentService;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.ProductService;
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
    private final PaymentService paymentService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderUseCase(UserService userService,
                        ProductService productService,
                        OrderService orderService,
                        PaymentService paymentService,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public OrderPaidResult order(Long userId, OrderRequest req) {
        User user = userService.getUser(userId);
        List<Product> products = productService.readProductsByIds(req.products().stream()
                .map(OrderRequest.ProductOrderRequest::id)
                .toList());

        Order order = orderService.order(user, products, req);

        Payment payment = paymentService.pay(user, order, req);

        applicationEventPublisher.publishEvent(new OrderCreatedEvent(products, req.products(), order, payment));
        return OrderPaidResult.of(order, payment);
    }
}
