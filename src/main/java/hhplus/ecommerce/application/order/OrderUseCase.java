package hhplus.ecommerce.application.order;

import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.cart.Cart;
import hhplus.ecommerce.domain.cart.CartService;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.event.OrderCreatedEvent;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.payment.PaymentService;
import hhplus.ecommerce.domain.product.StockService;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.domain.user.UserService;
import hhplus.ecommerce.storage.orderitem.OrderItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderUseCase {

    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final StockService stockService;
    private final PaymentService paymentService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderUseCase(UserService userService,
                        CartService cartService,
                        OrderService orderService,
                        ApplicationEventPublisher applicationEventPublisher,
                        StockService stockService,
                        PaymentService paymentService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.stockService = stockService;
        this.paymentService = paymentService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public OrderPaidResult order(Long userId, OrderRequest request) {
        User user = userService.getUser(userId);
        Cart cart = cartService.getCart(user);
        Order order = orderService.order(user, cart, request);

        try {
            for (OrderItem item : order.items()) {
                stockService.decreaseProductStock(item);
                orderService.updateItemStatus(item, OrderItemStatus.SUCCESS);
            }
            cartService.resetCart(user);
            Payment payment = paymentService.pay(user, order, request);

            applicationEventPublisher.publishEvent(new OrderCreatedEvent(order, payment));
            return OrderPaidResult.of(order, payment);
        } catch (Exception e) {
            orderService.orderFailed(order);
            stockService.compensationOrderStock(order);
            throw new RuntimeException(e.getMessage());
        }
    }
}
